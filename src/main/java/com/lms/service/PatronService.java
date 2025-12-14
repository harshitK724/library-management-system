package com.lms.service;

import com.lms.app.AppLogger;
import com.lms.domain.Patron;
import com.lms.repository.PatronRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class PatronService {
    private final Logger log = AppLogger.get();
    private final PatronRepository patronRepo;

    public PatronService(PatronRepository patronRepo) {
        this.patronRepo = patronRepo;
    }

    public void addPatron(Patron patron) {
        patronRepo.save(patron);
        log.info("Added patron: " + patron);
    }

    public void updatePatron(String patronId, String name, String email) {
        Patron p = patronRepo.findById(patronId)
                .orElseThrow(() -> new IllegalArgumentException("Patron not found: " + patronId));
        p.setName(name);
        p.setEmail(email);
        patronRepo.save(p);
        log.info("Updated patron: " + p);
    }

    public Optional<Patron> findById(String patronId) {
        return patronRepo.findById(patronId);
    }

    public List<Patron> allPatrons() {
        return patronRepo.findAll();
    }
}
