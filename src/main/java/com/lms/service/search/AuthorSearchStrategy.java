package com.lms.service.search;

import com.lms.domain.Book;
import java.util.List;

public class AuthorSearchStrategy implements BookSearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String query) {
        String q = query.toLowerCase();
        return books.stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(q))
                .toList();
    }
}
