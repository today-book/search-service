package com.todaybook.searchservice.application.rerank.service;

import com.todaybook.searchservice.application.emotion.EmotionType;

public interface EmotionClassifier {
  EmotionType classify(float[] embedding);
}
