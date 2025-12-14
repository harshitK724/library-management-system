package com.lms.repository;

import com.lms.domain.Patron;
import java.util.*;

public class InMemoryPatronRepository implements PatronRepository {
    private final Map<String, Patron> byId = new HashMap<>();

    @Override public void save(Patron patron) { byId.put(patron.getPatronId(), patron); }
    @Override public Optional<Patron> findById(String patronId) { return Optional.ofNullable(byId.get(patronId)); }
    @Override public List<Patron> findAll() { return new ArrayList<>(byId.values()); }
}
