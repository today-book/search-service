package com.todaybook.searchservice.application.rerank.calculator;

import com.todaybook.searchservice.domain.vo.EmotionType;
import org.springframework.stereotype.Component;

/**
 * 감정 유사도 점수를 계산하는 컴포넌트.
 *
 * <p>현재는 동일 감정이면 1.0, 아니면 0.0을 반환한다.
 *
 * @author 김지원
 * @since 1.0.0
 */
@Component
public class EmotionScoreCalculator {

  public double calculate(EmotionType bookEmotion, EmotionType targetEmotion) {
    return (bookEmotion == targetEmotion) ? 1.0 : 0.0;
  }
}
