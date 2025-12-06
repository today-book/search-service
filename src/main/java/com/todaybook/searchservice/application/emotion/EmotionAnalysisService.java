package com.todaybook.searchservice.application.emotion;

import com.todaybook.searchservice.application.emotion.dto.EmotionResult;

public interface EmotionAnalysisService {
  EmotionResult analyze(String query);
}
