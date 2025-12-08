package com.todaybook.searchservice.infrastructure.book;

import static java.util.stream.Collectors.toSet;

import com.todaybook.searchservice.application.book.BookInfoProvider;
import com.todaybook.searchservice.application.book.dto.BookInfo;
import com.todaybook.searchservice.infrastructure.feign.BookFeignClient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * HttpBookInfoProvider는 외부 Book 서비스와의 HTTP 통신(Feign)을 통해 도서 기본 정보(BookInfo)를 조회하는 인프라 계층 구현체이다.
 *
 * <p>요청한 bookId와 응답 결과 간에 일부 누락이 발생할 경우, 누락된 bookId 목록을 로그로 남겨 데이터 정합성 문제를 추적할 수 있도록 한다.
 *
 * @author 김지원
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class HttpBookInfoProvider implements BookInfoProvider {

  private final BookFeignClient bookFeignClient;

  /**
   * 도서 ID 목록을 기반으로 외부 Book 서비스에서 도서 정보를 조회한다.
   *
   * <p>일부 bookId가 응답에서 누락될 수 있으며, 이는 예외 상황으로 취급하지 않고 관측(log)만 수행한다.
   *
   * @param bookIds 조회할 도서 ID 목록
   * @return 조회된 도서 정보 목록
   */
  @Override
  public List<BookInfo> getBooksByIds(List<UUID> bookIds) {
    List<BookInfo> books = bookFeignClient.getBookByIds(bookIds);
    logMissingBookIdsIfAny(bookIds, books);
    return books;
  }

  /** 요청한 bookIds 대비 응답 결과에 누락된 bookId가 존재하는지 확인하고, 누락된 경우 경고 로그를 남긴다. */
  private void logMissingBookIdsIfAny(List<UUID> requestedIds, List<BookInfo> response) {
    Set<UUID> missingIds = findMissingBookIds(requestedIds, response);

    if (!missingIds.isEmpty()) {
      log.warn("Missing BookInfo detected. missingBookIds={}", missingIds);
    }
  }

  /** 요청된 bookIds 중 응답에 포함되지 않은 bookId 목록을 계산한다. */
  private Set<UUID> findMissingBookIds(List<UUID> requestedIds, List<BookInfo> response) {
    Set<UUID> responseIds = response.stream().map(BookInfo::id).collect(toSet());

    Set<UUID> missing = new HashSet<>(requestedIds);
    missing.removeAll(responseIds);
    return missing;
  }
}
