package com.todaybook.searchservice.application.emotion;

import com.todaybook.searchservice.domain.vo.EmotionType;

public record EmotionResult(EmotionType emotion, String query) {}
