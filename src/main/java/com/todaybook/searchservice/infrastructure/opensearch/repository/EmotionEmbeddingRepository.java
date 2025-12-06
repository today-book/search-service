package com.todaybook.searchservice.infrastructure.opensearch.repository;

import com.todaybook.searchservice.infrastructure.opensearch.document.EmotionEmbeddingDocument;
import com.todaybook.searchservice.infrastructure.opensearch.exception.OpenSearchAccessException;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmotionEmbeddingRepository {

  private static final String EMOTION_EMBEDDING_INDEX = "emotion-embedding-index";

  private final OpenSearchClient client;

  public void save(EmotionEmbeddingDocument doc) {
    try {
      client.index(
          i -> i.index(EMOTION_EMBEDDING_INDEX).id(doc.getEmotionType().name()).document(doc));
    } catch (IOException e) {
      throw new OpenSearchAccessException(
          "Failed to index emotion embedding document in OpenSearch.", e);
    }
  }

  public List<EmotionEmbeddingDocument> findAll() {
    try {
      SearchResponse<EmotionEmbeddingDocument> response =
          client.search(
              s -> s.index(EMOTION_EMBEDDING_INDEX).query(q -> q.matchAll(m -> m)),
              EmotionEmbeddingDocument.class);
      return response.hits().hits().stream().map(Hit::source).toList();
    } catch (IOException e) {
      throw new OpenSearchAccessException(
          "Failed to query emotion embedding documents from OpenSearch.", e);
    }
  }
}
