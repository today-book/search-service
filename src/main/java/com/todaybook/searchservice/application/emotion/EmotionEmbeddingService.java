package com.todaybook.searchservice.application.emotion;

import com.todaybook.searchservice.application.embedding.EmbeddingService;
import com.todaybook.searchservice.application.emotion.dto.EmotionRegister;
import com.todaybook.searchservice.domain.EmotionEmbedding;
import com.todaybook.searchservice.domain.EmotionEmbeddingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmotionEmbeddingService {

  private final EmotionEmbeddingRepository repository;
  private final EmbeddingService embeddingService;

  public void register(EmotionRegister emotionRegister) {
    float[] embedding = embeddingService.embed(emotionRegister.description());

    EmotionEmbedding emotionEmbedding =
        EmotionEmbedding.create(
            emotionRegister.emotionType(), emotionRegister.description(), embedding);

    repository.save(emotionEmbedding);
  }
}
