package com.todaybook.searchservice.infrastructure.opensearch;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.ai.vectorstore.opensearch")
public class OpenSearchClientProperties {

  private String uris;
}
