package com.lms.service;

import com.lms.domain.Book;
import com.lms.domain.BookStatus;
import com.lms.notification.*;
import com.lms.repository.*;

import java.util.*;

public class ReservationService {
    private final BookRepository bookRepo;
    private final PatronRepository patronRepo;

    // isbn -> queue of patronIds
    private final Map<String, Deque<String>> reservations = new HashMap<>();

    public ReservationService(BookRepository bookRepo, PatronRepository patronRepo) {
        this.bookRepo = bookRepo;
        this.patronRepo = patronRepo;
    }

    public void reserve(String isbn, String patronId) {
        Book book = bookRepo.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + isbn));
        patronRepo.findById(patronId)
                .orElseThrow(() -> new IllegalArgumentException("Patron not found: " + patronId));

        reservations.computeIfAbsent(isbn, k -> new ArrayDeque<>()).addLast(patronId);

        if (book.getStatus() == BookStatus.AVAILABLE) {
            book.setStatus(BookStatus.RESERVED);
            bookRepo.save(book);
        }
    }

    // call this when a book is returned
    public Optional<String> nextReservationPatron(String isbn) {
        Deque<String> q = reservations.get(isbn);
        if (q == null || q.isEmpty()) return Optional.empty();
        return Optional.of(q.removeFirst());
    }

    public void notifyPatronAvailable(String patronId, String isbn) {
        var patron = patronRepo.findById(patronId).orElseThrow();
        NotificationObserver observer = new EmailNotifier(patron.getEmail());
        observer.notify("Your reserved book is now available. ISBN=" + isbn);
    }
}


