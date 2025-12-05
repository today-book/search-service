package com.todaybook.searchservice.application.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookEmbeddingResponse {

  private UUID bookId;

  private String title;

  private List<String> categories;

  private String description;

  private double score;

  private double finalScore;

  private LocalDateTime createdAt;
}
