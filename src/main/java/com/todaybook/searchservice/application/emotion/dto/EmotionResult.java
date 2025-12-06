package com.todaybook.searchservice.application.emotion.dto;

import com.todaybook.searchservice.application.emotion.EmotionType;

public record EmotionResult(EmotionType emotion, String query) {}
