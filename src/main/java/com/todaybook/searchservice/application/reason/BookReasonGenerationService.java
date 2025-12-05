package com.todaybook.searchservice.application.reason;

import com.todaybook.searchservice.application.emotion.EmotionResult;
import com.todaybook.searchservice.application.rerank.dto.BookSearchResult;
import java.util.List;

public interface BookReasonGenerationService {

  List<BookReasonResult> generateReasons(List<BookSearchResult> books, EmotionResult emotionQuery);
}
