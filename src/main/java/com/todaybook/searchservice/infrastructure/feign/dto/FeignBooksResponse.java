package com.todaybook.searchservice.infrastructure.feign.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record FeignBooksResponse(List<FeignBookResponse> found, List<UUID> failure) {
  public record FeignBookResponse(
      UUID id,
      String isbn,
      String title,
      List<String> categories,
      String description,
      String author,
      String publisher,
      LocalDate publishedAt,
      String thumbnail) {}
}
