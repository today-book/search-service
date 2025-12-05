package com.todaybook.searchservice.infrastructure.gemini;

import com.todaybook.searchservice.application.emotion.EmotionAnalysisService;
import com.todaybook.searchservice.application.emotion.EmotionResult;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties(EmotionAnalysisProperties.class)
@Component
@RequiredArgsConstructor
public class GeminiEmotionAnalysisService implements EmotionAnalysisService {

  private final ChatModel chatModel;
  private final EmotionAnalysisProperties properties;

  private ChatClient chatClient;

  @Value("classpath:prompts/emotion_prompt.txt")
  private Resource promptTemplateResource;

  private String promptTemplate;

  @PostConstruct
  public void init() {
    try {
      ChatOptions defaultOption =
          ChatOptions.builder()
              .temperature(properties.getTemperature())
              .maxTokens(properties.getMaxTokens())
              .build();

      chatClient = ChatClient.builder(chatModel).defaultOptions(defaultOption).build();
      promptTemplate = promptTemplateResource.getContentAsString(StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new IllegalStateException(
          "Failed to load emotion prompt template from classpath:prompts/emotion_prompt.txt", e);
    } catch (Exception e) {
      throw new IllegalStateException("Failed to initialize GeminiEmotionAnalysisService", e);
    }
  }

  @Override
  public EmotionResult analyze(String query) {
    return chatClient
        .prompt()
        .user(u -> u.text(promptTemplate).param("query", query))
        .call()
        .entity(EmotionResult.class);
  }
}
