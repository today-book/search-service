package com.todaybook.searchservice.application.emotion.dto;

import com.todaybook.searchservice.application.emotion.EmotionType;

public record EmotionEmbeddingCommand(EmotionType emotionType, String description) {}
