package com.todaybook.searchservice.application.reason;

import java.util.UUID;

public record BookReason(UUID bookId, String reason, double score) {}
