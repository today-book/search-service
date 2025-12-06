package com.todaybook.searchservice.infrastructure.opensearch;

import com.todaybook.searchservice.application.emotion.EmotionEmbeddingService;
import com.todaybook.searchservice.application.emotion.dto.EmotionEmbeddingCommand;
import com.todaybook.searchservice.infrastructure.opensearch.document.EmotionType;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
public class EmotionEmbeddingDataLoader {

  @Autowired private EmotionEmbeddingService embeddingService;

  @Test
  void input() {
    List<EmotionEmbeddingCommand> emotionRegisters =
        List.of(
            new EmotionEmbeddingCommand(EmotionType.JOY, "기쁨 행복 유쾌함 밝음 활기 긍정적인 에너지. 슬픔이나 불안은 없음"),
            new EmotionEmbeddingCommand(EmotionType.SAD, "슬픔 우울 상실감 감정적 깊이 외로움. 기쁨이나 활기는 없음"),
            new EmotionEmbeddingCommand(EmotionType.FEAR, "공포 불안 위협 긴장 스릴 위험. 따뜻함이나 안정감은 없음"),
            new EmotionEmbeddingCommand(EmotionType.ANGER, "분노 격앙 갈등 억울함 공격성. 차분함이나 기쁨은 없음"),
            new EmotionEmbeddingCommand(EmotionType.HEALING, "위로 안정 치유 따뜻함 평온함 회복. 공포나 분노는 없음"),
            new EmotionEmbeddingCommand(EmotionType.MOTIVATION, "동기부여 영감 목표 성취 성장 의지 열정. 우울함은 없음"),
            new EmotionEmbeddingCommand(EmotionType.NEUTRAL, "중립 객관 분석적 논리적 사실 기반 감정 개입 없음"));

    emotionRegisters.forEach(e -> embeddingService.embedAndSaveEmotion(e));
  }

  @Test
  void update() {
    List<EmotionEmbeddingCommand> emotionRegisters =
        List.of(
            new EmotionEmbeddingCommand(EmotionType.JOY, "기쁨 행복 유쾌함 밝음 활기 긍정적인 에너지. 슬픔이나 불안은 없음"));

    emotionRegisters.forEach(e -> embeddingService.embedAndSaveEmotion(e));
  }
}
