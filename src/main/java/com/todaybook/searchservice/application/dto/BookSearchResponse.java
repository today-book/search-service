package com.todaybook.searchservice.application.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record BookSearchResponse(
    UUID bookId,
    String isbn,
    String title,
    String author,
    String description,
    List<String> categories,
    String publisher,
    LocalDate publishedAt,
    String thumbnail,
    double score,
    String reason) {}
