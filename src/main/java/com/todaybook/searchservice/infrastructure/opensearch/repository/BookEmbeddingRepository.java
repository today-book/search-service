package com.todaybook.searchservice.infrastructure.opensearch.repository;

import com.todaybook.searchservice.infrastructure.opensearch.document.BookEmbeddingDocument;
import com.todaybook.searchservice.infrastructure.opensearch.exception.OpenSearchAccessException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch._types.FieldValue;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookEmbeddingRepository {

  private static final String BOOK_EMBEDDING_INDEX = "book-embedding-index";

  private final OpenSearchClient client;

  public List<BookEmbeddingDocument> findAllByIds(Collection<UUID> ids) {
    try {
      List<FieldValue> fieldValues = ids.stream().map(UUID::toString).map(FieldValue::of).toList();

      SearchResponse<BookEmbeddingDocument> response =
          client.search(
              s ->
                  s.index(BOOK_EMBEDDING_INDEX)
                      .query(
                          q ->
                              q.terms(t -> t.field("id.keyword").terms(v -> v.value(fieldValues)))),
              BookEmbeddingDocument.class);

      return response.hits().hits().stream().map(Hit::source).toList();

    } catch (IOException e) {
      throw new OpenSearchAccessException(
          "Failed to query emotion embedding documents from OpenSearch.", e);
    }
  }
}
