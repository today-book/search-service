package com.todaybook.searchservice.infrastructure.opensearch.document;

import java.util.List;

public record BookMetadata(String title, String author, List<String> categories) {}
