package com.todaybook.searchservice.application.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "todaybook.search")
@Data
public class SearchProperties {

  private int vectorTopK;
  private int rerankTopN;
}
