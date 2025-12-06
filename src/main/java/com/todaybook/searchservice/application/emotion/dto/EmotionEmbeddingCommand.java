package com.todaybook.searchservice.application.emotion.dto;

import com.todaybook.searchservice.infrastructure.opensearch.document.EmotionType;

public record EmotionEmbeddingCommand(EmotionType emotionType, String description) {}
