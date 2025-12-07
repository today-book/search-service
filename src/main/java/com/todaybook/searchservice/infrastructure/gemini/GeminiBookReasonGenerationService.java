package com.todaybook.searchservice.infrastructure.gemini;

import com.todaybook.searchservice.application.emotion.dto.EmotionResult;
import com.todaybook.searchservice.application.reason.BookReasonGenerationService;
import com.todaybook.searchservice.application.reason.BookReasonResult;
import com.todaybook.searchservice.application.rerank.dto.BookSearchResult;
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

@EnableConfigurationProperties(BookReasonGenerationProperties.class)
@Component
@RequiredArgsConstructor
public class GeminiBookReasonGenerationService implements BookReasonGenerationService {

  private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

  private final ChatModel chatModel;
  private final BookReasonGenerationProperties properties;

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
      throw new IllegalStateException("Failed to initialize GeminiBookReasonGenerationService", e);
    }
  }

  @Override
  public List<BookReasonResult> generateReasons(
      List<BookSearchResult> books, EmotionResult emotionQuery) {

    List<CompletableFuture<BookReasonResult>> futures =
        books.stream()
            .map(
                book ->
                    CompletableFuture.supplyAsync(
                        () -> generateReason(book, emotionQuery), executor))
            .toList();

    return futures.stream().map(CompletableFuture::join).toList();
  }

  private BookReasonResult generateReason(BookSearchResult book, EmotionResult emotionQuery) {
    return chatClient
        .prompt()
        .user(
            u ->
                u.text(promptTemplate)
                    .param("emotion", emotionQuery.emotion())
                    .param("query", emotionQuery.query())
                    .param("bookId", book.getBookId().toString())
                    .param("title", book.getTitle())
                    .param("categories", book.getCategories())
                    .param("description", book.getDescription()))
        .call()
        .entity(BookReasonResult.class);
  }
}
