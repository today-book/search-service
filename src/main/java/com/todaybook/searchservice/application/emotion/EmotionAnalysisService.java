package com.todaybook.searchservice.application.emotion;

public interface EmotionAnalysisService {
  EmotionResult analyze(String query);
}
