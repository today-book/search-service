package com.todaybook.searchservice.application.reason;

import java.util.UUID;

public record BookReasonResult(UUID bookId, String reason, double score) {}
