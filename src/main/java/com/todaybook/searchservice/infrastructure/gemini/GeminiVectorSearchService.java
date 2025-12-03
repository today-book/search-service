package com.todaybook.searchservice.infrastructure.gemini;

import com.todaybook.searchservice.application.VectorSearchService;
import com.todaybook.searchservice.domain.BookEmbedding;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.todaybook.searchservice.infrastructure.DocumentMetadataUtils.*;

@Component
@RequiredArgsConstructor
public class GeminiVectorSearchService implements VectorSearchService {

    private final VectorStore vectorStore;

    @Override
    public List<BookEmbedding> searchTopN(String query, int topN) {

        SearchRequest request = buildSearchRequest(query, topN);
        List<Document> documents = vectorStore.similaritySearch(request);

        return documents.stream()
                .map(this::toBookEmbedding)
                .toList();
    }

    private SearchRequest buildSearchRequest(String query, int topK) {
        return SearchRequest.builder()
                .query(query)
                .topK(topK)
                .build();
    }

    /**
     * Document → BookEmbedding 변환
     */
    private BookEmbedding toBookEmbedding(Document doc) {
        Map<String, Object> metadata = doc.getMetadata();
        return BookEmbedding.builder()
                .bookId(parseUUID(doc.getId()))
                .title(getString(metadata, "title"))
                .categories(getStringList(metadata, "categories"))
                .createdAt(getDateTime(metadata, "createdAt"))
                .description(doc.getText())
                .score(Objects.requireNonNull(doc.getScore()))
                .build();
    }
}
