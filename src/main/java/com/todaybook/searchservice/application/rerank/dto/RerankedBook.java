package com.todaybook.searchservice.application.rerank.dto;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record RerankedBook(
    UUID bookId,
    String title,
    List<String> categories,
    String description,
    double score,
    double finalScore) {}
