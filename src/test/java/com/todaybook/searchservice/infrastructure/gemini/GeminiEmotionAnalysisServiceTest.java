package com.todaybook.searchservice.infrastructure.gemini;

import com.todaybook.searchservice.application.emotion.EmotionAnalysisService;
import com.todaybook.searchservice.application.emotion.EmotionResult;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class GeminiEmotionAnalysisServiceTest {

  @Autowired private EmotionAnalysisService emotionAnalysisService;

  @Test
  void result() {
    String text = "책 추천 \n" + "사람의 성격: 차분한, 엉뚱한 \n" + "부가 요구사항: 추리 소설";
    EmotionResult result = emotionAnalysisService.analyze(text);
    System.out.println("## Emotion = " + result.emotion());
    System.out.println("## Query   = " + result.query());
  }
}
