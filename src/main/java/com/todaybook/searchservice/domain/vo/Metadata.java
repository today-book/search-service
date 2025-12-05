package com.todaybook.searchservice.domain.vo;

import jakarta.persistence.Embeddable;
import java.util.List;

@Embeddable
public record Metadata(String title, List<String> categories) {}
