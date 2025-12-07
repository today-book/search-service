package com.todaybook.searchservice.infrastructure.gemini;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "custom.vertex-ai.book-reason-generation")
public class BookReasonGeneratorProperties {
  private double temperature;
  private int maxTokens;
}
