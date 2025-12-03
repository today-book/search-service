package com.todaybook.searchservice.infrastructure.embedding;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.vertexai.embedding.VertexAiEmbeddingConnectionDetails;
import org.springframework.ai.vertexai.embedding.text.VertexAiTextEmbeddingModel;
import org.springframework.ai.vertexai.embedding.text.VertexAiTextEmbeddingOptions;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(EmbeddingProperties.class)
public class VertexAiEmbeddingConfig {

    private final EmbeddingProperties embeddingProperties;

    @Bean
    public VertexAiEmbeddingConnectionDetails connectionDetails() {
        return VertexAiEmbeddingConnectionDetails.builder()
                .projectId(embeddingProperties.getProjectId())
                .location(embeddingProperties.getLocation())
                .build();
    }

    @Bean
    public VertexAiTextEmbeddingOptions embeddingOptions() {
        return VertexAiTextEmbeddingOptions.builder()
                .model(embeddingProperties.getModel())
                .build();
    }

    @Bean
    public VertexAiTextEmbeddingModel embeddingModel(VertexAiEmbeddingConnectionDetails connectionDetails, VertexAiTextEmbeddingOptions options) {
        return new VertexAiTextEmbeddingModel(connectionDetails, options);
    }
}
