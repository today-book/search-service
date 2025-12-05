package com.todaybook.searchservice.application.vector;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class ScoredBookId {
  private UUID bookId;
  private double score;
}
