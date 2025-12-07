package com.todaybook.searchservice.application.reason;

import java.util.List;
import java.util.UUID;

public record BookReasonResults(List<BookReasonResult> reasons) {

  public List<UUID> bookIds() {
    return reasons.stream().map(BookReasonResult::bookId).toList();
  }
}
