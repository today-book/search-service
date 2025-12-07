package com.todaybook.searchservice.application.dto;

import com.todaybook.searchservice.application.book.dto.BookInfo;
import com.todaybook.searchservice.application.reason.BookReasonResult;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookResponseMapper {

  /** BookSearchResult + BookReasonResult → BookResponse 변환 */
  public static List<BookResponse> map(
      List<BookInfo> bookInfos, List<BookReasonResult> reasonResults) {
    Map<UUID, BookReasonResult> reasonMap = toReasonMap(reasonResults);

    return bookInfos.stream()
        .map(book -> toResponse(book, reasonMap.get(book.id())))
        .sorted(Comparator.comparing(BookResponse::score).reversed())
        .toList();
  }

  private static BookResponse toResponse(BookInfo book, BookReasonResult reason) {
    return BookResponse.builder()
        .bookId(book.id())
        .isbn(book.isbn())
        .title(book.title())
        .author(book.author())
        .description(book.description())
        .categories(book.categories())
        .publisher(book.publisher())
        .publishedAt(book.publishedAt())
        .thumbnail(book.thumbnail())
        .score(reason.score())
        .reason(reason.reason())
        .build();
  }

  /** bookId → BookReasonResult Map 변환 */
  private static Map<UUID, BookReasonResult> toReasonMap(List<BookReasonResult> reasons) {
    return reasons.stream().collect(Collectors.toMap(BookReasonResult::bookId, r -> r));
  }
}
