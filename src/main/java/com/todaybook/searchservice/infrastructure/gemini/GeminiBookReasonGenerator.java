package com.todaybook.searchservice.infrastructure.gemini;

import com.todaybook.searchservice.application.emotion.dto.EmotionResult;
import com.todaybook.searchservice.application.reason.BookReason;
import com.todaybook.searchservice.application.reason.BookReasonGenerator;
import com.todaybook.searchservice.application.reason.BookReasons;
import com.todaybook.searchservice.application.rerank.dto.RerankedBook;
import com.todaybook.searchservice.application.rerank.dto.RerankedBooks;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties(BookReasonGeneratorProperties.class)
@Component
@RequiredArgsConstructor
public class GeminiBookReasonGenerator implements BookReasonGenerator {

  private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

  private final ChatModel chatModel;
  private final BookReasonGeneratorProperties properties;

  private ChatClient chatClient;

  @Value("classpath:prompts/reason_prompt.txt")
  private Resource promptTemplateResource;

  private String promptTemplate;

  @PostConstruct
  public void init() {
    try {
      ChatOptions defaultOption =
          ChatOptions.builder()
              .temperature(properties.getTemperature())
              .maxTokens(properties.getMaxTokens())
              .topP(0.8)
              .topK(40)
              .frequencyPenalty(0.0)
              .presencePenalty(0.0)
              .build();

      chatClient = ChatClient.builder(chatModel).defaultOptions(defaultOption).build();
      promptTemplate = promptTemplateResource.getContentAsString(StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new IllegalStateException(
          "Failed to load emotion prompt template from classpath:prompts/reason_prompt.txt", e);
    } catch (Exception e) {
      throw new IllegalStateException("Failed to initialize GeminiBookReasonGenerator", e);
    }
  }

  @Override
  public BookReasons generateReasons(RerankedBooks books, EmotionResult emotionQuery) {

    List<CompletableFuture<BookReason>> futures =
        books.values().stream()
            .map(
                book ->
                    CompletableFuture.supplyAsync(
                        () -> generateReason(book, emotionQuery), executor))
            .toList();

    List<BookReason> bookReasonList = futures.stream().map(CompletableFuture::join).toList();
    return new BookReasons(bookReasonList);
  }

  private BookReason generateReason(RerankedBook book, EmotionResult emotionQuery) {
    return chatClient
        .prompt()
        .user(
            u ->
                u.text(promptTemplate)
                    .param("emotion", emotionQuery.emotion())
                    .param("query", emotionQuery.query())
                    .param("bookId", book.bookId().toString())
                    .param("title", book.title())
                    .param("categories", book.categories())
                    .param("description", book.description()))
        .call()
        .entity(BookReason.class);
  }
}
