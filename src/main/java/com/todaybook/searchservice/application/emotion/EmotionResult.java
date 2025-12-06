package com.todaybook.searchservice.application.emotion;

import com.todaybook.searchservice.infrastructure.opensearch.document.EmotionType;

public record EmotionResult(EmotionType emotion, String query) {}
