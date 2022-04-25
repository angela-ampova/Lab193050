package com.example.lab193050.service;

import com.example.lab193050.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);
    Optional<Author> create(String name, String surname, Long country);
    List<Author> listAll();
}
