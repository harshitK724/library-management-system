package com.lms.service.search;

import com.lms.domain.Book;
import java.util.List;

public interface BookSearchStrategy {
    List<Book> search(List<Book> books, String query);
}
