package com.todaybook.searchservice.application.embedding;

public interface EmbeddingService {

  float[] embed(String text);
}
