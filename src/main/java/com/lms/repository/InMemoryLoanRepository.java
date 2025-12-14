package com.lms.repository;

import com.lms.domain.Loan;
import java.util.*;

public class InMemoryLoanRepository implements LoanRepository {
    private final Map<String, Loan> byLoanId = new HashMap<>();

    @Override public void save(Loan loan) { byLoanId.put(loan.getLoanId(), loan); }

    @Override public Optional<Loan> findActiveLoanByIsbn(String isbn) {
        return byLoanId.values().stream()
                .filter(l -> l.getIsbn().equals(isbn) && l.isActive())
                .findFirst();
    }

    @Override public List<Loan> findLoansByPatron(String patronId) {
        return byLoanId.values().stream()
                .filter(l -> l.getPatronId().equals(patronId))
                .toList();
    }

    @Override public List<Loan> findAll() { return new ArrayList<>(byLoanId.values()); }
}
