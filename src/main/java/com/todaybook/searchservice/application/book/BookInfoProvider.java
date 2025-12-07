package com.todaybook.searchservice.application.book;

import com.todaybook.searchservice.application.book.dto.BookInfo;
import java.util.List;
import java.util.UUID;

public interface BookInfoProvider {

  List<BookInfo> getBooksByIds(List<UUID> bookIds);
}
