package com.todaybook.searchservice.infrastructure.embedding;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "embedding.predict")
public record EmbeddingPredictTimeoutProperties(
    int initialRpcTimeoutSecond,
    int maxRpcTimeoutSecond,
    int totalTimeoutSecond
    ) {
}
