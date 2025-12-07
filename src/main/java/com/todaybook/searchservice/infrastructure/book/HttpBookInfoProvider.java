package com.todaybook.searchservice.infrastructure.book;

import com.todaybook.searchservice.application.book.BookInfoProvider;
import com.todaybook.searchservice.application.book.dto.BookInfo;
import com.todaybook.searchservice.infrastructure.feign.BookFeignClient;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpBookInfoProvider implements BookInfoProvider {

  private final BookFeignClient bookFeignClient;

  @Override
  public List<BookInfo> getBooksByIds(List<UUID> bookIds) {
    return bookFeignClient.getBookByIds(bookIds);
  }
}
