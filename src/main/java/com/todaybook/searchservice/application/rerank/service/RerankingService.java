package com.todaybook.searchservice.application.rerank.service;

import com.todaybook.searchservice.application.rerank.calculator.EmotionScoreCalculator;
import com.todaybook.searchservice.application.rerank.calculator.FinalScoreCalculator;
import com.todaybook.searchservice.application.rerank.dto.BookSearchResult;
import com.todaybook.searchservice.application.rerank.mapper.BookEmbeddingResponseMapper;
import com.todaybook.searchservice.application.rerank.model.BookEmbeddingScoreContext;
import com.todaybook.searchservice.application.vector.ScoredBookId;
import com.todaybook.searchservice.domain.BookEmbedding;
import com.todaybook.searchservice.domain.BookEmbeddingRepository;
import com.todaybook.searchservice.domain.vo.EmotionType;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * RerankingService는 벡터 검색 Top-N 후보에 대해 감정 기반 점수를 계산하고, 최종 점수를 재정렬하여 사용자에게 최적의 추천 결과를 제공하는 서비스이다.
 *
 * <p>1) 벡터 검색 초기 점수(score) 2) 도서 임베딩의 감정 분류(emotion) 3) 목표 감정(target emotion)과의 유사도(emotionScore)
 *
 * <p>이 세 요소를 기반으로 최종 점수(finalScore)를 계산하고 정렬한다.
 *
 * @author 김지원
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class RerankingService {

  private final EmotionClassifier emotionClassifier;
  private final BookEmbeddingRepository embeddingRepository;
  private final EmotionScoreCalculator emotionScoreCalculator;
  private final FinalScoreCalculator finalScoreCalculator;
  private final BookEmbeddingResponseMapper responseMapper;

  /**
   * 주어진 후보 도서 리스트(Top-N)를 감정 기반 점수를 포함해 재정렬한다.
   *
   * @param candidates 벡터 검색으로 조회한 Top-N 도서 목록
   * @param targetEmotion 사용자가 가진 목표 감정(추천 기준)
   * @return 감정 + 벡터 점수 기반으로 재정렬된 추천 도서 목록
   */
  public List<BookSearchResult> rerank(
      List<ScoredBookId> candidates, EmotionType targetEmotion, int limit) {

    // 검색 결과를 빠르게 조회하기 위해 Map<BookId, ScoredBookId> 로 변환
    Map<UUID, ScoredBookId> scoredBookIdMap = toScoredBookIdMap(candidates);

    // 실제 도서 임베딩 로드
    List<BookEmbedding> embeddings =
        embeddingRepository.findAllByIdIn(scoredBookIdMap.keySet().stream().toList());

    // 각 도서에 대해 감정 점수 + 최종 점수를 계산하여 정렬 후 Response 로 변환
    return embeddings.stream()
        .map(embedding -> calculateScoreContext(embedding, scoredBookIdMap, targetEmotion))
        .sorted(Comparator.comparing(BookEmbeddingScoreContext::finalScore).reversed())
        .limit(limit)
        .map(responseMapper::toResponse)
        .toList();
  }

  /**
   * 도서 한 건에 대해 감정 점수와 최종 점수를 계산하여 ScoreContext를 생성한다.
   *
   * @param embedding 도서의 임베딩 데이터
   * @param scoredBookIdMap 초기 벡터 검색 점수를 가진 Map
   * @param targetEmotion 사용자 목표 감정
   * @return BookEmbeddingScoreContext 최종 점수 계산 결과
   */
  private BookEmbeddingScoreContext calculateScoreContext(
      BookEmbedding embedding, Map<UUID, ScoredBookId> scoredBookIdMap, EmotionType targetEmotion) {

    // 초기 벡터 검색 점수 가져오기
    ScoredBookId scoredBookId = scoredBookIdMap.get(embedding.getId());
    if (scoredBookId == null) {
      throw new IllegalStateException("ScoredBookId not found for embedding: " + embedding.getId());
    }

    // 임베딩으로부터 도서의 감정 분류
    EmotionType bookEmotion = emotionClassifier.classify(embedding.getEmbedding());

    // 감정 유사도 점수 계산
    double emotionScore = emotionScoreCalculator.calculate(bookEmotion, targetEmotion);

    // 최종 점수 계산 (초기 점수 + 감정 점수)
    double finalScore = finalScoreCalculator.calculate(scoredBookId.getScore(), emotionScore);

    return new BookEmbeddingScoreContext(
        embedding, scoredBookId.getScore(), emotionScore, finalScore);
  }

  /**
   * 후보 도서를 빠르게 조회하기 위한 Map 변환 메서드.
   *
   * @param scoredBookIds 초기 vector 검색 결과 리스트
   * @return Map<bookId, ScoredBookId>
   */
  private Map<UUID, ScoredBookId> toScoredBookIdMap(List<ScoredBookId> scoredBookIds) {
    return scoredBookIds.stream().collect(Collectors.toMap(ScoredBookId::getBookId, c -> c));
  }
}
