package com.lms.repository;

import com.lms.domain.Patron;
import java.util.List;
import java.util.Optional;

public interface PatronRepository {
    void save(Patron patron);
    Optional<Patron> findById(String patronId);
    List<Patron> findAll();
}
