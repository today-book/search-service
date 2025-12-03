package com.todaybook.searchservice.infrastructure.embedding;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Optional;

@Data
@ConfigurationProperties(prefix = "spring.ai.vertex.ai.embedding")
public class EmbeddingProperties {
    private String projectId;
    private String location;

    @Getter(AccessLevel.NONE)
    private Text text;

    public String getModel() {
        return Optional.ofNullable(text)
                .map(Text::getOptions)
                .map(Options::getModel)
                .orElseThrow(() -> new IllegalStateException("Embedding configuration is not properly initialized"));
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
