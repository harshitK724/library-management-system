package com.lms.repository;

import com.lms.domain.Book;
import java.util.*;

public interface BookRepository {
    void save(Book book);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findAll();
    void deleteByIsbn(String isbn);
}
