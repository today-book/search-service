package com.todaybook.searchservice.application.rerank.service;

import com.todaybook.searchservice.infrastructure.opensearch.document.EmotionType;

public interface EmotionClassifier {
  EmotionType classify(float[] embedding);
}
