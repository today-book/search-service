package com.todaybook.searchservice.application;

import com.todaybook.searchservice.application.dto.BookEmbeddingResponse;
import com.todaybook.searchservice.application.emotion.EmotionAnalysisService;
import com.todaybook.searchservice.application.emotion.EmotionResult;
import com.todaybook.searchservice.application.rerank.service.RerankingService;
import com.todaybook.searchservice.application.vector.ScoredBookId;
import com.todaybook.searchservice.application.vector.VectorSearchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

  private final EmotionAnalysisService emotionAnalysisService;
  private final VectorSearchService vectorSearchService;
  private final RerankingService rerankingService;

  public List<BookEmbeddingResponse> search(String query, int topN) {
    EmotionResult emotionResult = emotionAnalysisService.analyze(query);

    List<ScoredBookId> candidates = vectorSearchService.searchTopN(emotionResult.query(), topN);

    return rerankingService.rerank(candidates, emotionResult.emotion(), topN);
  }
}
