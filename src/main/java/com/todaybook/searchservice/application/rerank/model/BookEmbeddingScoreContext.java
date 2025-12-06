package com.todaybook.searchservice.application.rerank.model;

import com.todaybook.searchservice.infrastructure.opensearch.document.BookEmbeddingDocument;

public record BookEmbeddingScoreContext(
    BookEmbeddingDocument embedding, double vectorScore, double emotionScore, double finalScore) {}
