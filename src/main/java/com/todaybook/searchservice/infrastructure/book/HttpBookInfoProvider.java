package com.todaybook.searchservice.infrastructure.book;

import com.todaybook.searchservice.application.book.BookInfoProvider;
import com.todaybook.searchservice.application.book.dto.BookInfo;
import com.todaybook.searchservice.infrastructure.feign.BookFeignClient;
import com.todaybook.searchservice.infrastructure.feign.dto.FeignBooksResponse;
import com.todaybook.searchservice.infrastructure.feign.mapper.BookInfoMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 외부 도서 서비스(Book Service)로부터 도서 정보를 조회하는 HTTP 기반 {@link BookInfoProvider} 구현체.
 *
 * <p>이 클래스는 Feign Client를 사용하여 외부 API를 호출하고, 외부 서비스 전용 DTO를 애플리케이션 내부에서 사용하는 {@link BookInfo} 형태로
 * 변환하여 제공한다.
 *
 * <p>주요 역할은 다음과 같다:
 *
 * <ul>
 *   <li>외부 API 호출 책임을 애플리케이션 계층으로부터 분리
 *   <li>외부 응답 DTO({@link FeignBooksResponse})를 내부 DTO({@link BookInfo})로 변환
 *   <li>외부 스펙 변경으로부터 애플리케이션 계층 보호
 * </ul>
 *
 * <p>애플리케이션 계층은 이 Provider를 통해서만 도서 정보를 조회하며, Feign 또는 외부 API 스펙에 직접 의존하지 않는다.
 *
 * @author 김지원
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class HttpBookInfoProvider implements BookInfoProvider {

  /** 외부 도서 서비스 호출을 담당하는 Feign Client. */
  private final BookFeignClient bookFeignClient;

  /**
   * 도서 ID 목록을 기반으로 외부 도서 서비스에서 도서 정보를 조회한다.
   *
   * <p>조회 요청 시 ID 목록이 {@code null}이거나 비어 있는 경우, 불필요한 외부 호출을 방지하기 위해 빈 리스트를 즉시 반환한다.
   *
   * <p>외부 서비스 응답에 포함된 조회 실패 ID 목록({@link FeignBooksResponse#failure()})은 로그로 기록하여 데이터 정합성 및 연계 장애 분석에
   * 활용한다.
   *
   * @param bookIds 조회할 도서 ID 목록
   * @return 조회된 도서 정보를 담은 {@link BookInfo} 리스트
   */
  @Override
  public List<BookInfo> getBooksByIds(List<UUID> bookIds) {
    if (bookIds == null || bookIds.isEmpty()) {
      return List.of();
    }

    FeignBooksResponse bookListResponse = bookFeignClient.getBookByIds(bookIds);

    if (bookListResponse.failure() != null && !bookListResponse.failure().isEmpty()) {
      log.warn("Missing BookInfo detected. missingBookIds={}", bookListResponse.failure());
    }

    return bookListResponse.found().stream().map(BookInfoMapper::toBookInfo).toList();
  }
}
