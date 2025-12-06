package com.todaybook.searchservice.application.rerank.service;

import com.todaybook.searchservice.infrastructure.opensearch.document.EmotionType;
import com.todaybook.searchservice.infrastructure.opensearch.repository.EmotionEmbeddingRepository;
import jakarta.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CosineSimilarityEmotionClassifier implements EmotionClassifier {

  private final EmotionEmbeddingRepository emotionEmbeddingRepository;
  private final Map<EmotionType, float[]> emotionVectors = new ConcurrentHashMap<>();

  @PostConstruct
  public void initEmotionVectors() {
    emotionEmbeddingRepository
        .findAll()
        .forEach(e -> emotionVectors.put(e.getEmotionType(), e.getEmbedding()));
  }

  @Override
  public EmotionType classify(float[] embedding) {

    EmotionType bestEmotion = null;
    double bestScore = -1;

    for (var entry : emotionVectors.entrySet()) {
      double score = CosineSimilarityCalculator.calculate(embedding, entry.getValue());
      if (score > bestScore) {
        bestScore = score;
        bestEmotion = entry.getKey();
      }
    }

    return bestEmotion;
  }
}
