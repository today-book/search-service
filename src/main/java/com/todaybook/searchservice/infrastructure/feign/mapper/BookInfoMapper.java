package com.todaybook.searchservice.infrastructure.feign.mapper;

import com.todaybook.searchservice.application.book.dto.BookInfo;
import com.todaybook.searchservice.infrastructure.feign.dto.FeignBooksResponse.FeignBookResponse;

public final class BookInfoMapper {

  private BookInfoMapper() {}

  public static BookInfo toBookInfo(FeignBookResponse source) {
    return new BookInfo(
        source.id(),
        source.isbn(),
        source.title(),
        source.author(),
        source.description(),
        source.categories(),
        source.publisher(),
        source.publishedAt(),
        source.thumbnail());
  }
}
