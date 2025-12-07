package com.todaybook.searchservice.application.reason;

import com.todaybook.searchservice.application.emotion.dto.EmotionResult;
import com.todaybook.searchservice.application.rerank.dto.BookSearchResult;
import java.util.List;

public interface BookReasonGenerator {

  BookReasons generateReasons(List<BookSearchResult> books, EmotionResult emotionQuery);
}
