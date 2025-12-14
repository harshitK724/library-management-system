package com.lms.service;

import com.lms.app.AppLogger;
import com.lms.domain.*;
import com.lms.repository.*;

import java.time.LocalDate;
import java.util.UUID;
import java.util.logging.Logger;

public class LendingService {
    private final Logger log = AppLogger.get();
    private final BookRepository bookRepo;
    private final PatronRepository patronRepo;
    private final LoanRepository loanRepo;

    public LendingService(BookRepository bookRepo, PatronRepository patronRepo, LoanRepository loanRepo) {
        this.bookRepo = bookRepo;
        this.patronRepo = patronRepo;
        this.loanRepo = loanRepo;
    }

    public void checkout(String isbn, String patronId) {
        Book book = bookRepo.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + isbn));
        Patron patron = patronRepo.findById(patronId)
                .orElseThrow(() -> new IllegalArgumentException("Patron not found: " + patronId));

        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new IllegalStateException("Book not available: " + isbn + " status=" + book.getStatus());
        }

        book.setStatus(BookStatus.BORROWED);
        bookRepo.save(book);

        String loanId = UUID.randomUUID().toString();
        Loan loan = new Loan(loanId, isbn, patronId, LocalDate.now());
        loanRepo.save(loan);

        patron.recordBorrowedIsbn(isbn);
        patronRepo.save(patron);

        log.info("Checkout successful: isbn=" + isbn + " patron=" + patronId + " loanId=" + loanId);
    }

    public void returnBook(String isbn) {
        Book book = bookRepo.findByIsbn(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + isbn));

        Loan loan = loanRepo.findActiveLoanByIsbn(isbn)
                .orElseThrow(() -> new IllegalStateException("No active loan for isbn=" + isbn));

        loan.markReturned(LocalDate.now());
        loanRepo.save(loan);

        book.setStatus(BookStatus.AVAILABLE);
        bookRepo.save(book);

        log.info("Return successful: isbn=" + isbn + " loanId=" + loan.getLoanId());
    }
}
