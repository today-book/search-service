package com.todaybook.searchservice.application;

import com.todaybook.searchservice.application.dto.EmotionResult;

public interface EmotionAnalysisService {
  EmotionResult analyze(String query);
}
