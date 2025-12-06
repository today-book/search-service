package com.todaybook.searchservice.infrastructure.opensearch;

import com.todaybook.searchservice.infrastructure.opensearch.document.BookEmbeddingDocument;
import com.todaybook.searchservice.infrastructure.opensearch.repository.BookEmbeddingRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
public class OpenSearchNativeClientTest {

  @Autowired BookEmbeddingRepository repository;

  @Test
  void findByIds() {
    UUID id1 = UUID.fromString("0f3e8f0c-e9e0-45e0-b073-684a4dfd8a11");
    UUID id2 = UUID.fromString("3fa3a9bb-8a89-4a56-9e03-5dc20f33ef58");

    List<BookEmbeddingDocument> bookEmbeddingDocuments = repository.findAllByIds(List.of(id1, id2));
    System.out.println("end");
  }
}
