package com.lms.repository;

import com.lms.domain.Loan;
import java.util.*;

public interface LoanRepository {
    void save(Loan loan);
    Optional<Loan> findActiveLoanByIsbn(String isbn);
    List<Loan> findLoansByPatron(String patronId);
    List<Loan> findAll();
}
