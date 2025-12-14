package com.lms.domain;

import java.util.*;

public class Patron {
    private final String patronId;     // identity
    private String name;
    private String email;

    // track borrowing history (Loan records will be stored elsewhere too)
    private final List<String> borrowedIsbnsHistory = new ArrayList<>();

    public Patron(String patronId, String name, String email) {
        this.patronId = Objects.requireNonNull(patronId);
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
    }

    public String getPatronId() { return patronId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<String> getBorrowedIsbnsHistory() { return Collections.unmodifiableList(borrowedIsbnsHistory); }

    public void setName(String name) { this.name = Objects.requireNonNull(name); }
    public void setEmail(String email) { this.email = Objects.requireNonNull(email); }

    public void recordBorrowedIsbn(String isbn) { borrowedIsbnsHistory.add(isbn); }

    @Override public String toString() {
        return "Patron{id='%s', name='%s', email='%s'}".formatted(patronId, name, email);
    }
}
