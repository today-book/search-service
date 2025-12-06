package com.todaybook.searchservice.infrastructure.opensearch.document;

import com.todaybook.searchservice.application.emotion.EmotionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmotionEmbeddingDocument {

  private EmotionType emotionType;

  private String description;

  private float[] embedding;
}
