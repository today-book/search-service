package com.todaybook.searchservice.application;

import com.todaybook.searchservice.domain.BookEmbedding;

import java.util.List;

public interface VectorSearchService {
    List<BookEmbedding> searchTopN(String query, int topN);
}
