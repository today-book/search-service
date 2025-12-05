package com.todaybook.searchservice.infrastructure.embedding;

import com.todaybook.searchservice.application.embedding.EmbeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GeminiEmbeddingService implements EmbeddingService {

  private final EmbeddingModel model;

  @Override
  public float[] embed(String text) {
    return model.embed(text);
  }
}
