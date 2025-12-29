package com.todaybook.searchservice.infrastructure.embedding;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.threeten.bp.Duration;

@ConfigurationProperties(prefix = "embedding.predict")
public record EmbeddingPredictTimeoutProperties(
    Duration totalTimeout,
    Duration initialRpcTimeout,
    Duration maxRpcTimeout
) {
}
