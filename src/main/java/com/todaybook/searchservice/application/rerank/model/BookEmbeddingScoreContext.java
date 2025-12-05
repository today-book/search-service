package com.todaybook.searchservice.application.rerank.model;

import com.todaybook.searchservice.domain.BookEmbedding;

public record BookEmbeddingScoreContext(
    BookEmbedding embedding, double vectorScore, double emotionScore, double finalScore) {}
