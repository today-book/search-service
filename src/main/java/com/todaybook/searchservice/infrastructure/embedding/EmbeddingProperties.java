package com.todaybook.searchservice.infrastructure.embedding;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.ai.vertex.ai.embedding")
public class EmbeddingProperties {
    private String projectId;
    private String location;

    @Getter(AccessLevel.NONE)
    private Text text;

    public String getModel() {
        return text.options.model;
    }

    @Data
    public static class Text {
        private Options options;
    }

    @Data
    public static class Options {
        private String model;
    }
}
