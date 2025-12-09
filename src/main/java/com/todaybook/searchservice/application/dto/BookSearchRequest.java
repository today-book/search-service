package com.todaybook.searchservice.application.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record BookSearchRequest(String query, @NotNull List<String> emotions) {}
