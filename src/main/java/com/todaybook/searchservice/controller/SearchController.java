package com.todaybook.searchservice.controller;

import com.todaybook.searchservice.application.SearchService;
import com.todaybook.searchservice.application.dto.BookSearchRequest;
import com.todaybook.searchservice.application.dto.BookSearchResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/search")
@RestController
@RequiredArgsConstructor
public class SearchController {

  private final SearchService service;

  @GetMapping("/books")
  public List<BookSearchResponse> search(BookSearchRequest request) {
    return service.search(request);
  }
}
