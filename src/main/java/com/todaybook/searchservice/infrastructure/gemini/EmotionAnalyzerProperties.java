package com.todaybook.searchservice.infrastructure.gemini;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "todaybook.search.vertex-ai.emotion-analysis")
public class EmotionAnalyzerProperties {
  private double temperature;
  private int maxTokens;
}
