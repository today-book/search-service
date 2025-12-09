package com.todaybook.searchservice.infrastructure.feign;

import com.todaybook.searchservice.infrastructure.feign.dto.FeignBooksResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "book-service", path = "/internal/v1/books")
public interface BookFeignClient {

  @PostMapping("/ids")
  FeignBooksResponse getBookByIds(@RequestBody List<UUID> ids);
}
