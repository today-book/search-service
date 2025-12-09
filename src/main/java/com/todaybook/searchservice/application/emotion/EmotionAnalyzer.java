package com.todaybook.searchservice.application.emotion;

import com.todaybook.searchservice.application.emotion.dto.EmotionResult;

public interface EmotionAnalyzer {
  EmotionResult analyze(String query);
}
