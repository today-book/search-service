package com.todaybook.searchservice.application.vector;

import java.util.List;

public interface VectorSearchService {
  List<ScoredBookId> searchTopN(String query, int topN);
}
