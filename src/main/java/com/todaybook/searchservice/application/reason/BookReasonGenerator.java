package com.todaybook.searchservice.application.reason;

import com.todaybook.searchservice.application.emotion.dto.EmotionResult;
import com.todaybook.searchservice.application.rerank.dto.RerankedBooks;

public interface BookReasonGenerator {

  BookReasons generateReasons(RerankedBooks books, EmotionResult emotionQuery);
}
