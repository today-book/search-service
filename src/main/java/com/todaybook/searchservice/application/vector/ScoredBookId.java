package com.todaybook.searchservice.application.vector;

import java.util.UUID;

public record ScoredBookId(UUID bookId, double score) {}
