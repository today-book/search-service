package com.todaybook.searchservice.application.rerank.calculator;

import org.springframework.stereotype.Component;

/**
 * 벡터 점수와 감정 점수를 가중합하여 최종 점수를 계산하는 컴포넌트.
 *
 * @author 김지원
 * @since 1.0.0
 */
@Component
public class FinalScoreCalculator {

  private static final double WEIGHT_VECTOR = 0.70;
  private static final double WEIGHT_EMOTION = 0.30;

  public double calculate(double vectorScore, double emotionScore) {
    return (vectorScore * WEIGHT_VECTOR) + (emotionScore * WEIGHT_EMOTION);
  }
}
