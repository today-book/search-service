package com.todaybook.searchservice.infrastructure.feign;

import com.todaybook.searchservice.application.book.dto.BookInfo;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "book-service", path = "/api/v1/books", url = "http://")
public interface BookFeignClient {

  @PostMapping("/ids")
  List<BookInfo> getBookByIds(@RequestBody List<UUID> ids);
}
