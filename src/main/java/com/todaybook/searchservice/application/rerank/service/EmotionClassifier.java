package com.todaybook.searchservice.application.rerank.service;

import com.todaybook.searchservice.domain.vo.EmotionType;

public interface EmotionClassifier {
  EmotionType classify(float[] embedding);
}
