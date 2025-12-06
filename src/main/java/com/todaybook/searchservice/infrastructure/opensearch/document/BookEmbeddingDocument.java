package com.todaybook.searchservice.infrastructure.opensearch.document;

import java.util.UUID;
import lombok.Data;

@Data
public class BookEmbeddingDocument {

  private UUID id;

  private String content;

  private BookMetadata metadata;

  private float[] embedding;
}
