package com.todaybook.searchservice.application.dto;

import java.util.List;

public record BookSearchRequest(String query, List<String> emotions) {}
