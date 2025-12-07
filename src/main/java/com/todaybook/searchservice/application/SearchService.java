package com.todaybook.searchservice.application;

import com.todaybook.searchservice.application.config.SearchProperties;
import com.todaybook.searchservice.application.dto.BookResponse;
import com.todaybook.searchservice.application.dto.BookResponseMapper;
import com.todaybook.searchservice.application.emotion.EmotionAnalysisService;
import com.todaybook.searchservice.application.emotion.dto.EmotionResult;
import com.todaybook.searchservice.application.reason.BookReasonGenerationService;
import com.todaybook.searchservice.application.reason.BookReasonResult;
import com.todaybook.searchservice.application.rerank.dto.BookSearchResult;
import com.todaybook.searchservice.application.rerank.service.RerankingService;
import com.todaybook.searchservice.application.vector.ScoredBookId;
import com.todaybook.searchservice.application.vector.VectorSearchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * SearchService는 사용자의 검색 질의를 기반으로 감정 분석부터 벡터 검색, 재랭킹, LLM 기반 추천 이유 생성, 최종 응답 조립까지 전체 검색 파이프라인을
 * 수행한다.
 *
 * <p>핵심 처리 단계:
 *
 * <ul>
 *   <li>1) EmotionAnalysisService → 감정 분석 및 임베딩 검색용 Query 생성
 *   <li>2) VectorSearchService → 벡터 유사도 기반 Top-N 후보 검색
 *   <li>3) RerankingService → 감정 기반 재랭킹 점수 계산
 *   <li>4) BookReasonGenerationService → LLM 기반 추천 이유 및 적합도 점수 생성
 *   <li>5) BookResponseMapper → 최종 결과 DTO 조립 및 점수 기준 정렬
 * </ul>
 *
 * @author 김지원
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(SearchProperties.class)
public class SearchService {

  private final EmotionAnalysisService emotionAnalysisService;
  private final VectorSearchService vectorSearchService;
  private final RerankingService rerankingService;
  private final BookReasonGenerationService bookReasonGenerationService;

  private final SearchProperties searchProperties;

  /**
   * 사용자의 검색 질의를 기반으로 전체 검색 프로세스를 실행한다.
   *
   * @param query 사용자 검색 문장
   * @return 최종 추천 도서 목록
   */
  public List<BookResponse> search(String query) {

    // 1. 사용자의 감정 분석 및 벡터 검색용 Query 재작성
    EmotionResult emotion = emotionAnalysisService.analyze(query);

    // 2. 벡터 유사도 기반 후보 Top-N 검색
    List<ScoredBookId> candidates =
        searchVectorCandidates(emotion.query(), searchProperties.getVectorTopK());

    // 3. 감정 기반 재랭킹 (코사인 점수 + 감정 점수 조합)
    List<BookSearchResult> reranked =
        rerankCandidates(candidates, emotion, searchProperties.getRerankTopN());

    // 4. LLM 기반 추천 이유 및 적합도 점수 생성
    List<BookReasonResult> bookReasonResults = generateRecommendReason(reranked, emotion);

    // 5. 도서 정보 + 추천 이유를 조합하여 최종 응답 DTO 생성
    return BookResponseMapper.map(reranked, bookReasonResults);
  }

  /**
   * 벡터 검색 서비스를 통해 유사도 기반 후보를 조회한다.
   *
   * @param rewrittenQuery 감정 분석을 통해 재작성된 쿼리
   * @param topN 가져올 후보 개수
   * @return 벡터 검색 후보 목록
   */
  private List<ScoredBookId> searchVectorCandidates(String rewrittenQuery, int topN) {
    return vectorSearchService.searchTopN(rewrittenQuery, topN);
  }

  /**
   * 후보 도서 리스트에 대해 감정 기반 재랭킹을 수행한다.
   *
   * @param candidates 벡터 검색 결과 후보
   * @param emotion 감정 분석 결과
   * @return 재랭킹된 도서 리스트
   */
  private List<BookSearchResult> rerankCandidates(
      List<ScoredBookId> candidates, EmotionResult emotion, int topK) {
    return rerankingService.rerank(candidates, emotion.emotion(), topK);
  }

  /**
   * LLM을 이용해 추천 이유(reason)와 적합도(score)를 생성한다.
   *
   * @param books 재랭킹된 도서 리스트
   * @param emotion 감정 분석 결과
   * @return 추천 이유 생성 결과 목록
   */
  private List<BookReasonResult> generateRecommendReason(
      List<BookSearchResult> books, EmotionResult emotion) {
    return bookReasonGenerationService.generateReasons(books, emotion);
  }
}
