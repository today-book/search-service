package com.todaybook.searchservice.infrastructure.opensearch;

import java.net.URISyntaxException;
import org.apache.hc.core5.http.HttpHost;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.httpclient5.ApacheHttpClient5TransportBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(OpenSearchClientProperties.class)
@Configuration
public class OpenSearchConfig {

  @Bean
  public OpenSearchClient openSearchClient(OpenSearchClientProperties properties) {
    try {
      HttpHost host = HttpHost.create(properties.getUris());

      OpenSearchTransport transport =
          ApacheHttpClient5TransportBuilder.builder(host)
              .setMapper(new JacksonJsonpMapper())
              .build();

      return new OpenSearchClient(transport);
    } catch (URISyntaxException e) {
      throw new IllegalStateException(
          "Invalid OpenSearch URI configured: " + properties.getUris(), e);
    }
  }
}
