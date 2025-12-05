package com.todaybook.searchservice.application.emotion.dto;

import com.todaybook.searchservice.domain.vo.EmotionType;

public record EmotionRegister(EmotionType emotionType, String description) {}
