package com.lms.service.search;

import com.lms.domain.Book;
import java.util.List;

public class IsbnSearchStrategy implements BookSearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String query) {
        return books.stream()
                .filter(b -> b.getIsbn().equalsIgnoreCase(query))
                .toList();
    }
}
