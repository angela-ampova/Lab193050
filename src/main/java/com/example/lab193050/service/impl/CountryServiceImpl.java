package com.example.lab193050.service.impl;

import com.example.lab193050.model.Country;
import com.example.lab193050.repository.CountryRepository;
import com.example.lab193050.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);

    }

    @Override
    public Optional<Country> create(String name, String continent) {
        Country country  = new Country(name,continent);
        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }
}
