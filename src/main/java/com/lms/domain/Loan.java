package com.lms.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {
    private final String loanId;
    private final String isbn;
    private final String patronId;
    private final LocalDate checkoutDate;
    private LocalDate returnDate;

    public Loan(String loanId, String isbn, String patronId, LocalDate checkoutDate) {
        this.loanId = Objects.requireNonNull(loanId);
        this.isbn = Objects.requireNonNull(isbn);
        this.patronId = Objects.requireNonNull(patronId);
        this.checkoutDate = Objects.requireNonNull(checkoutDate);
    }

    public String getLoanId() { return loanId; }
    public String getIsbn() { return isbn; }
    public String getPatronId() { return patronId; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isActive() { return returnDate == null; }

    public void markReturned(LocalDate date) { this.returnDate = Objects.requireNonNull(date); }
}
