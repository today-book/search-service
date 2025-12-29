package com.todaybook.searchservice.infrastructure.embedding;

import com.google.api.gax.retrying.RetrySettings;
import com.google.cloud.aiplatform.v1.PredictionServiceSettings;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.vertexai.embedding.VertexAiEmbeddingConnectionDetails;
import org.springframework.ai.vertexai.embedding.text.VertexAiTextEmbeddingModel;
import org.springframework.ai.vertexai.embedding.text.VertexAiTextEmbeddingOptions;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties({EmbeddingProperties.class, EmbeddingPredictTimeoutProperties.class})
public class GeminiEmbeddingConfig {

  private final EmbeddingProperties embeddingProperties;
  private final EmbeddingPredictTimeoutProperties timeoutProperties;

  @Bean
  public VertexAiEmbeddingConnectionDetails connectionDetails() throws IOException {
    PredictionServiceSettings.Builder pss = PredictionServiceSettings.newBuilder();
    applyPredictTimeouts(pss);

    return VertexAiEmbeddingConnectionDetails.builder()
        .projectId(embeddingProperties.getProjectId())
        .location(embeddingProperties.getLocation())
        .predictionServiceSettings(pss.build())
        .build();
  }

  @Bean
  public VertexAiTextEmbeddingOptions embeddingOptions() {
    return VertexAiTextEmbeddingOptions.builder()
        .model(embeddingProperties.getModel())
        .dimensions(embeddingProperties.getDimensions())
        .build();
  }

  @Bean
  public VertexAiTextEmbeddingModel embeddingModel(
      VertexAiEmbeddingConnectionDetails connectionDetails, VertexAiTextEmbeddingOptions options) {
    return new VertexAiTextEmbeddingModel(connectionDetails, options);
  }

  private void applyPredictTimeouts(PredictionServiceSettings.Builder pss) {
    RetrySettings retry = pss.predictSettings().getRetrySettings().toBuilder()
        .setTotalTimeout(timeoutProperties.totalTimeout())
        .setInitialRpcTimeout(timeoutProperties.initialRpcTimeout())
        .setMaxRpcTimeout(timeoutProperties.maxRpcTimeout())
        .build();

    pss.predictSettings().setRetrySettings(retry);
  }
}
