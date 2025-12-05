package com.todaybook.searchservice.application.rerank.mapper;

import com.todaybook.searchservice.application.rerank.dto.BookSearchResult;
import com.todaybook.searchservice.application.rerank.model.BookEmbeddingScoreContext;
import com.todaybook.searchservice.domain.BookEmbedding;
import org.springframework.stereotype.Component;

/**
 * BookEmbedding 엔티티를 BookEmbeddingResponse DTO로 변환하는 Mapper.
 *
 * @author 김지원
 * @since 1.0.0
 */
@Component
public class BookEmbeddingResponseMapper {

  public BookSearchResult toResponse(BookEmbeddingScoreContext context) {
    BookEmbedding embedding = context.embedding();

    return BookSearchResult.builder()
        .bookId(embedding.getId())
        .title(embedding.getMetadata().title())
        .categories(embedding.getMetadata().categories())
        .description(embedding.getContent())
        .score(context.vectorScore())
        .finalScore(context.finalScore())
        .build();
  }
}
