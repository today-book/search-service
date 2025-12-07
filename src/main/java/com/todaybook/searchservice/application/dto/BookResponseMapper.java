package com.todaybook.searchservice.application.dto;

import com.todaybook.searchservice.application.book.dto.BookInfo;
import com.todaybook.searchservice.application.reason.BookReason;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookResponseMapper {

  /** bookInfos + bookReasons → BookResponse 변환 */
  public static List<BookResponse> map(List<BookInfo> bookInfos, List<BookReason> bookReasons) {
    Map<UUID, BookReason> reasonMap = toReasonMap(bookReasons);

    return bookInfos.stream()
        .map(book -> toResponse(book, reasonMap.get(book.id())))
        .sorted(Comparator.comparing(BookResponse::score).reversed())
        .toList();
  }

  private static BookResponse toResponse(BookInfo book, BookReason reason) {
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

  /** BookReason → (bookId, BookReason) Map 변환 */
  private static Map<UUID, BookReason> toReasonMap(List<BookReason> reasons) {
    return reasons.stream().collect(Collectors.toMap(BookReason::bookId, r -> r));
  }
}
