package com.todaybook.searchservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@ToString
public class BookEmbedding {

    private UUID bookId;

    private String title;

    private List<String> categories;

    private String description;

    private double score;

    private LocalDateTime createdAt;
}
