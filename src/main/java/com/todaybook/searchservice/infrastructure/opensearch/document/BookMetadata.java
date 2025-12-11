package com.todaybook.searchservice.infrastructure.opensearch.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookMetadata(String title, String author, List<String> categories) {}
