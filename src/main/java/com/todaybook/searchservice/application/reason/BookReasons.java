package com.todaybook.searchservice.application.reason;

import java.util.List;
import java.util.UUID;

public record BookReasons(List<BookReason> reasons) {

  public List<UUID> bookIds() {
    return reasons.stream().map(BookReason::bookId).toList();
  }
}
