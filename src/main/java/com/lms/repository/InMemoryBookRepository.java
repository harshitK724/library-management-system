package com.lms.repository;

import com.lms.domain.Book;
import java.util.*;

public class InMemoryBookRepository implements BookRepository {
    private final Map<String, Book> byIsbn = new HashMap<>();

    @Override public void save(Book book) { byIsbn.put(book.getIsbn(), book); }
    @Override public Optional<Book> findByIsbn(String isbn) { return Optional.ofNullable(byIsbn.get(isbn)); }
    @Override public List<Book> findAll() { return new ArrayList<>(byIsbn.values()); }
    @Override public void deleteByIsbn(String isbn) { byIsbn.remove(isbn); }
}
