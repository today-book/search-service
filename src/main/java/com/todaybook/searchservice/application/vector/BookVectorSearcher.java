package com.todaybook.searchservice.application.vector;

public interface BookVectorSearcher {
  ScoredBookIds searchTopK(String query, int topK);
}
