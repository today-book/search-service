package com.todaybook.searchservice.application.dto;

import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record BookResponse(
    UUID bookId,
    String title,
    List<String> categories,
    String description,
    double score,
    String reason) {}
