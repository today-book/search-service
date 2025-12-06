package com.todaybook.searchservice.application.emotion;

import com.todaybook.searchservice.application.embedding.EmbeddingService;
import com.todaybook.searchservice.application.emotion.dto.EmotionEmbeddingCommand;
import com.todaybook.searchservice.infrastructure.opensearch.document.EmotionEmbeddingDocument;
import com.todaybook.searchservice.infrastructure.opensearch.repository.EmotionEmbeddingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmotionEmbeddingService {

  private final EmotionEmbeddingRepository repository;
  private final EmbeddingService embeddingService;

  public void embedAndSaveEmotion(EmotionEmbeddingCommand command) {
    float[] embedding = embeddingService.embed(command.description());

    EmotionEmbeddingDocument emotionEmbedding =
        new EmotionEmbeddingDocument(command.emotionType(), command.description(), embedding);

    repository.save(emotionEmbedding);
  }
}
