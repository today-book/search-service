package com.todaybook.searchservice.domain.vo;

import java.util.Objects;
import java.util.UUID;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class BookId {

  private UUID id;

  private BookId(UUID id) {
    this.id = Objects.requireNonNull(id);
  }

  public UUID toUuid() {
    return id;
  }

  public static BookId of(UUID uuid) {
    return new BookId(uuid);
  }

  @Override
  public String toString() {
    return id.toString();
  }
}
