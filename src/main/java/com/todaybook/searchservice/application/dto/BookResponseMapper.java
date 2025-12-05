package com.todaybook.searchservice.application.dto;

import com.todaybook.searchservice.application.reason.BookReasonResult;
import com.todaybook.searchservice.application.rerank.dto.BookSearchResult;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookResponseMapper {

  /** BookSearchResult + BookReasonResult → BookResponse 변환 */
  public static List<BookResponse> map(
      List<BookSearchResult> reranked, List<BookReasonResult> reasonResults) {
    Map<UUID, BookReasonResult> reasonMap = toReasonMap(reasonResults);

    return reranked.stream()
        .map(book -> toResponse(book, reasonMap.get(book.getBookId())))
        .sorted(Comparator.comparing(BookResponse::score).reversed())
        .toList();
  }

  /** bookId → BookReasonResult Map 변환 */
  private static Map<UUID, BookReasonResult> toReasonMap(List<BookReasonResult> reasons) {
    return reasons.stream().collect(Collectors.toMap(BookReasonResult::bookId, r -> r));
  }

  private static BookResponse toResponse(BookSearchResult book, BookReasonResult reason) {
    return BookResponse.builder()
        .bookId(book.getBookId())
        .title(book.getTitle())
        .categories(book.getCategories())
        .description(book.getDescription())
        .score(reason.score())
        .reason(reason.reason())
        .build();
  }
}
