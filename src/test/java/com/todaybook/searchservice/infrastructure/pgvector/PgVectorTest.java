package com.todaybook.searchservice.infrastructure.pgvector;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
public class PgVectorTest {
  @Autowired VectorStore vectorStore;

  @Test
  void save() {
    List<Document> documents =
        List.of(
            new Document(
                "0f3e8f0c-e9e0-45e0-b073-684a4dfd8a11",
                "작은 습관이 장기적인 변화를 이끈다는 핵심 원리를 다루며, 일상 속 루틴 설계법을 제시한 세계적 베스트셀러.",
                Map.of(
                    "title", "아주 작은 습관의 힘 (Atomic Habits)", "categories", List.of("자기계발", "심리학"))),
            new Document(
                "7b44f6de-df68-42a8-b0c8-0fb37f05b7dd",
                "삶에서 더 큰 성취와 행복을 얻기 위해 중요한 원칙과 마음가짐을 제시한 전 세계 밀리언셀러.",
                Map.of("title", "미라클 모닝", "categories", List.of("자기계발", "라이프스타일"))),
            new Document(
                "3fa3a9bb-8a89-4a56-9e03-5dc20f33ef58",
                "인생의 본질과 죽음, 존재에 대해 깊이 있게 성찰하며 삶의 태도에 대해 질문을 던지는 철학 에세이.",
                Map.of("title", "죽은 시인의 사회", "categories", List.of("문학", "에세이"))));
    vectorStore.add(documents);
  }

  @Test
  void cosine_similarity() {
    List<Document> results =
        this.vectorStore.similaritySearch(
            SearchRequest.builder().query("호기심을 자극하며 학습의 본질을 탐구하도록 동기를 부여하는 책").topK(10).build());
    System.out.println(results);
  }

  @Test
  void update() {
    vectorStore.delete(List.of("3fa3a9bb-8a89-4a56-9e03-5dc20f33ef58"));
    List<Document> documents =
        List.of(
            new Document(
                "3fa3a9bb-8a89-4a56-9e03-5dc20f33ef58",
                "인생의 본질과 죽음, 존재에 대해 깊이 있게 성찰하며 삶의 태도에 대해 질문을 던지는 철학 에세이.",
                Map.of("title", "죽은 시인의 사회", "categories", List.of("문학", "에세이"))));
    vectorStore.add(documents);
  }
}
