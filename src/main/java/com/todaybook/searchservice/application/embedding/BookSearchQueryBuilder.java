package com.todaybook.searchservice.application.embedding;

import com.todaybook.searchservice.application.dto.BookSearchRequest;

/**
 * BookSearchRequest를 임베딩 입력 텍스트로 변환하는 책임을 가진 클래스.
 *
 * <p>검색 의도와 감정 상태를 자연어 문장으로 재구성하여 벡터 검색 품질을 높이기 위한 입력을 생성합니다.
 *
 * @author 김지원
 * @since 1.0.0
 */
public final class BookSearchQueryBuilder {

  private BookSearchQueryBuilder() {}

  public static String build(BookSearchRequest request) {
    return """
사용자는 다음 조건에 맞는 책을 찾고 있다.
주제: %s
현재 감정: %s
"""
        .formatted(request.query(), String.join(", ", request.emotions()));
  }
}
