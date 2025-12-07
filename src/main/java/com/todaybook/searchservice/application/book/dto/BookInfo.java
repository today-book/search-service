package com.todaybook.searchservice.application.book.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record BookInfo(
    UUID id,
    String isbn,
    String title,
    String author,
    String description,
    List<String> categories,
    String publisher,
    LocalDateTime publishedAt,
    String thumbnail) {}
