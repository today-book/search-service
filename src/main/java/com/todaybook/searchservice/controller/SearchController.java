package com.todaybook.searchservice.controller;

import com.todaybook.searchservice.application.SearchService;
import com.todaybook.searchservice.application.dto.BookResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {

  private final SearchService service;

  @GetMapping("/books")
  public List<BookResponse> search(String query) {
    return service.search(query);
  }
}
