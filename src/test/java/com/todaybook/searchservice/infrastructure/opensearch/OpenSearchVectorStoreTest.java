package com.todaybook.searchservice.infrastructure.opensearch;

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
public class OpenSearchVectorStoreTest {
  @Autowired VectorStore vectorStore;

  @Test
  void save() {
    List<Document> documents =
        List.of(
            new Document(
                "e418b9c2-3d4e-4a1b-9f18-000000005018",
                "현대 사진 예술: 다큐멘터리 사진과 예술 사진의 경계를 탐구한다. 사진이 현실을 기록하는 동시에 해석과 조작의 결과물일 수 있음을 논의하며, 작가의 시선이 이미지의 의미를 어떻게 규정하는지를 보여준다. 이미지 소비 시대에 사진의 역할을 재고하게 만든다.",
                Map.of("title", "현대 사진 예술", "categories", List.of("예술", "사진"))),
            new Document(
                "e419b9c2-3d4e-4a1b-9f19-000000005019",
                "공공 예술의 역할: 예술이 미술관을 넘어 도시 공간과 일상에 개입하는 방식을 다룬다. 공공 예술 프로젝트가 사회적 메시지 전달과 공동체 형성에 어떤 영향을 미치는지를 분석하며, 예술의 사회적 책임과 참여성에 대해 질문을 던진다.",
                Map.of("title", "공공 예술의 역할", "categories", List.of("예술", "공공미술"))),
            new Document(
                "e420b9c2-3d4e-4a1b-9f20-000000005020",
                "예술과 기술의 만남: 디지털 기술이 예술 표현 방식을 어떻게 확장했는지를 설명한다. 인터랙티브 아트와 미디어 아트 사례를 통해 관객 참여형 예술의 특징을 분석하며, 기술 발전이 예술 창작의 개념 자체를 변화시키는 과정을 다룬다.",
                Map.of("title", "예술과 기술의 만남", "categories", List.of("예술", "미디어아트"))));
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
    List<Document> documents =
        List.of(
            new Document(
                "e420b9c2-3d4e-4a1b-9f20-000000005020",
                "예술과 기술의 만남: 디지털 기술이 예술 표현 방식을 어떻게 확장했는지를 설명한다. 인터랙티브 아트와 미디어 아트 사례를 통해 관객 참여형 예술의 특징을 분석하며, 기술 발전이 예술 창작의 개념 자체를 변화시키는 과정을 다룬다.",
                Map.of("title", "예술과 기술의 만남", "categories", List.of("예술", "미디어아트"))));
    vectorStore.add(documents);
  }
}
