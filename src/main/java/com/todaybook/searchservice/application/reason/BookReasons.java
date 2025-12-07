package com.todaybook.searchservice.application.reason;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public record BookReasons(List<BookReason> values) {

  public List<UUID> bookIds() {
    return values.stream().map(BookReason::bookId).toList();
  }

  public Map<UUID, BookReason> toReasonMap() {
    return values.stream().collect(Collectors.toMap(BookReason::bookId, Function.identity()));
  }
}
