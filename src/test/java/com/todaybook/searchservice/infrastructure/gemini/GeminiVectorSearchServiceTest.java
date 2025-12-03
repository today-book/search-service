package com.todaybook.searchservice.infrastructure.gemini;

import com.todaybook.searchservice.application.dto.EmotionResult;
import com.todaybook.searchservice.domain.BookEmbedding;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class GeminiVectorSearchServiceTest {

    @Autowired
    GeminiVectorSearchService vectorSearchService;

    @Autowired
    GeminiEmotionAnalysisService emotionAnalysisService;

    @Test
    void cosine() {
        List<BookEmbedding> bookEmbeddings = vectorSearchService.searchTopN("재미 있는 책", 10);
        System.out.println(bookEmbeddings);
    }

    @Test
    void integration() {
        String query = """
                책 추천
                사람의 성격: 외향적이며 사회적 자극을 즐기는
                부가 요구사항: 사람 관계 이해, 커뮤니케이션
                """;
        EmotionResult emotionResult = emotionAnalysisService.analyze(query);
        System.out.println("emotionResult = " + emotionResult);

        List<BookEmbedding> bookEmbeddings = vectorSearchService.searchTopN(emotionResult.query(), 10);
        System.out.println("bookEmbeddings = " + bookEmbeddings);
    }
}