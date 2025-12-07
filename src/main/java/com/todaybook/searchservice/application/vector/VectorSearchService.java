package com.todaybook.searchservice.application.vector;

public interface VectorSearchService {
  ScoredBookIds searchTopK(String query, int topK);
}
