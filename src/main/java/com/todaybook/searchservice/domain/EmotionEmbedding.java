package com.todaybook.searchservice.domain;

import com.todaybook.searchservice.domain.converter.FloatArrayToVectorConverter;
import com.todaybook.searchservice.domain.vo.EmotionType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "p_emotion_embeddings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmotionEmbedding {

  @Id
  @Enumerated(EnumType.STRING)
  private EmotionType emotionType;

  private String description;

  @Convert(converter = FloatArrayToVectorConverter.class)
  @Column(columnDefinition = "vector(3072)")
  private float[] embedding;

  private EmotionEmbedding(EmotionType emotionType, String description, float[] embedding) {
    this.emotionType = Objects.requireNonNull(emotionType);
    this.description = Objects.requireNonNull(description);
    this.embedding = Objects.requireNonNull(embedding);
  }

  public static EmotionEmbedding create(
      EmotionType emotionType, String description, float[] embedding) {
    return new EmotionEmbedding(emotionType, description, embedding);
  }
}
