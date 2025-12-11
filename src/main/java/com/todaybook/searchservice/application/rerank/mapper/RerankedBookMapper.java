package com.todaybook.searchservice.application.rerank.mapper;

import com.todaybook.searchservice.application.rerank.dto.RerankedBook;
import com.todaybook.searchservice.application.rerank.model.BookEmbeddingScoreContext;
import com.todaybook.searchservice.infrastructure.opensearch.document.BookEmbeddingDocument;
import org.springframework.stereotype.Component;

/**
 * BookEmbeddingScoreContext 를 RerankedBook DTO로 변환하는 Mapper.
 *
 * @author 김지원
 * @since 1.0.0
 */
@Component
public class RerankedBookMapper {

  public RerankedBook toResponse(BookEmbeddingScoreContext context) {
    BookEmbeddingDocument embedding = context.embedding();

    return RerankedBook.builder()
        .bookId(embedding.getId())
        .title(embedding.getMetadata().title())
        .categories(embedding.getMetadata().categories())
        .description(embedding.getContent())
        .score(context.vectorScore())
        .finalScore(context.finalScore())
        .build();
  }
}
