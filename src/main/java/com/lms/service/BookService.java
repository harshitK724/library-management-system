package com.lms.service;

import com.lms.app.AppLogger;
import com.lms.domain.Book;
import com.lms.repository.BookRepository;
import com.lms.service.search.BookSearchStrategy;
import com.lms.service.search.SearchStrategyFactory;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class BookService {
    private final Logger log = AppLogger.get();
    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void addBook(Book book) {
        bookRepo.save(book);
        log.info("Added book: " + book);
    }

    public void removeBook(String isbn) {
        bookRepo.deleteByIsbn(isbn);
        log.info("Removed book ISBN=" + isbn);
    }

    public void updateBook(String isbn, String title, String author, int year) {
        Book book = bookRepo.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + isbn));
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublicationYear(year);
        bookRepo.save(book);
        log.info("Updated book: " + book);
    }

    public Optional<Book> findByIsbn(String isbn) {
        return bookRepo.findByIsbn(isbn);
    }

    public List<Book> search(SearchStrategyFactory.SearchType type, String query) {
    BookSearchStrategy strategy = SearchStrategyFactory.get(type);
    return strategy.search(bookRepo.findAll(), query);
}

    public List<Book> allBooks() {
        return bookRepo.findAll();
    }
}
