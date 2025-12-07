package com.todaybook.searchservice.application.reason;

import java.util.List;
import java.util.UUID;

public record BookReasons(List<BookReason> values) {

  public List<UUID> bookIds() {
    return values.stream().map(BookReason::bookId).toList();
  }
}
