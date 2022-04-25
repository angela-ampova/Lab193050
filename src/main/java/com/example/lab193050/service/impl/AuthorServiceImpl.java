package com.example.lab193050.service.impl;

import com.example.lab193050.model.Author;
import com.example.lab193050.model.Country;
import com.example.lab193050.model.exceptions.CountryNotFoundException;
import com.example.lab193050.repository.AuthorRepository;
import com.example.lab193050.repository.CountryRepository;
import com.example.lab193050.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> create(String name, String surname, Long country) {
        Country country1 = this.countryRepository.findById(country)
                .orElseThrow(()->new CountryNotFoundException(country));
        Author author = new Author(name,surname,country1);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }
}

