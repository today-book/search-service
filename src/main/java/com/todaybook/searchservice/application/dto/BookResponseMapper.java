package com.todaybook.searchservice.application.dto;

import com.todaybook.searchservice.application.book.dto.BookInfo;
import com.todaybook.searchservice.application.reason.BookReason;
import com.todaybook.searchservice.application.reason.BookReasons;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

/**
 * BookResponseMapper는 BookInfo와 BookReason을 결합하여 API 응답용 BookResponse 목록으로 변환하는 책임을 가진 매퍼 클래스이다.
 *
 * <p>BookInfo에 대응되는 BookReason이 존재하지 않는 경우, 해당 항목은 변환 대상에서 제외되며 로그로만 관측한다.
 *
 * @author 김지원
 * @since 1.0.0
 */
@Slf4j
public class BookResponseMapper {

  /**
   * BookInfo 목록과 BookReasons를 결합하여 BookResponse 목록으로 변환한다.
   *
   * <p>BookReason이 누락된 BookInfo는 응답에서 제외되며, 최종 결과는 추천 점수(score) 기준 내림차순으로 정렬된다.
   *
   * @param bookInfos 조회된 도서 메타 정보 목록
   * @param bookReasons 추천 사유 및 점수 일급 컬렉션
   * @return 점수 기준으로 정렬된 BookResponse 목록
   */
  public static List<BookSearchResponse> map(List<BookInfo> bookInfos, BookReasons bookReasons) {
    Map<UUID, BookReason> reasonMap = bookReasons.toReasonMap();

    return bookInfos.stream()
        .map(book -> toResponseIfPossible(book, reasonMap))
        .flatMap(Optional::stream)
        .sorted(Comparator.comparing(BookSearchResponse::score).reversed())
        .toList();
  }

  /**
   * BookInfo에 대응되는 BookReason이 존재하는 경우에만 BookResponse로 변환한다.
   *
   * <p>BookReason이 누락된 경우 로그만 남기고 빈 Optional을 반환한다.
   */
  private static Optional<BookSearchResponse> toResponseIfPossible(
      BookInfo book, Map<UUID, BookReason> reasonMap) {

    BookReason reason = reasonMap.get(book.id());
    if (reason == null) {
      log.warn("BookReason missing for bookId={}. BookResponse mapping skipped.", book.id());
      return Optional.empty();
    }

    return Optional.of(toResponse(book, reason));
  }

  /**
   * BookInfo와 BookReason을 결합하여 BookResponse로 변환한다.
   *
   * <p>이 메서드는 BookReason이 null이 아님을 전제로 한다.
   *
   * @param book 도서 메타 정보
   * @param reason 추천 사유 및 점수 정보
   * @return API 응답용 BookResponse
   */
  private static BookSearchResponse toResponse(BookInfo book, BookReason reason) {
    return BookSearchResponse.builder()
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
}
