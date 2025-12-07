package com.todaybook.searchservice.infrastructure.gemini;

import com.todaybook.searchservice.application.vector.ScoredBookId;
import com.todaybook.searchservice.application.vector.ScoredBookIds;
import com.todaybook.searchservice.application.vector.VectorSearchService;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeminiVectorSearchService implements VectorSearchService {

  private final VectorStore vectorStore;

  @Override
  public ScoredBookIds searchTopK(String query, int topK) {

    SearchRequest request = buildSearchRequest(query, topK);
    List<Document> documents = vectorStore.similaritySearch(request);

    List<ScoredBookId> scoredBookIdList = documents.stream().map(this::toScoredBookId).toList();
    return new ScoredBookIds(scoredBookIdList);
  }

  private SearchRequest buildSearchRequest(String query, int topK) {
    return SearchRequest.builder().query(query).topK(topK).build();
  }

  /** Document → BookEmbedding 변환 */
  private ScoredBookId toScoredBookId(Document doc) {
    return new ScoredBookId(
        UUID.fromString(Objects.requireNonNull(doc.getId())),
        Objects.requireNonNull(doc.getScore()));
  }
}
