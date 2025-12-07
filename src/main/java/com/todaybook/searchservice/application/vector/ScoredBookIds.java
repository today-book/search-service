package com.todaybook.searchservice.application.vector;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public record ScoredBookIds(List<ScoredBookId> values) {

  public Map<UUID, ScoredBookId> toBookIdMap() {
    return values().stream().collect(Collectors.toMap(ScoredBookId::bookId, Function.identity()));
  }
}
