package com.todaybook.searchservice.infrastructure.gemini;

import com.todaybook.searchservice.application.EmotionAnalysisService;
import com.todaybook.searchservice.application.dto.EmotionResult;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class GeminiEmotionAnalysisService implements EmotionAnalysisService {

    private final ChatModel chatModel;

    private ChatClient chatClient;

    @Value("classpath:prompts/emotion_prompt.txt")
    private Resource promptTemplateResource;

    private String promptTemplate;


    @PostConstruct
    public void init() {
        ChatOptions defaultOption = ChatOptions.builder()
                .temperature(0.1)
                .maxTokens(200)
                .build();

        chatClient = ChatClient.builder(chatModel)
                .defaultOptions(defaultOption)
                .build();

        try {
            promptTemplate = promptTemplateResource.getContentAsString(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load emotion prompt template.", e);
        }
    }

    @Override
    public EmotionResult analyze(String query) {
        return chatClient.prompt()
                .user(u -> u.text(promptTemplate)
                        .param("query", query))
                .call()
                .entity(EmotionResult.class);
    }
}
