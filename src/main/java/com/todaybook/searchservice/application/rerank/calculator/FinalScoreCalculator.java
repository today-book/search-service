package com.todaybook.searchservice.application.rerank.calculator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 벡터 점수와 감정 점수를 가중합하여 최종 점수를 계산하는 컴포넌트.
 *
 * @author 김지원
 * @since 1.0.0
 */
@Component
@EnableConfigurationProperties(RerankWeightProperties.class)
@RequiredArgsConstructor
public class FinalScoreCalculator {

  private final RerankWeightProperties rerankWeightProperties;

  public double calculate(double vectorScore, double emotionScore) {
    return (vectorScore * rerankWeightProperties.vector())
        + (emotionScore * rerankWeightProperties.emotion());
  }
}
