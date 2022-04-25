package com.example.lab193050.model.dto;

import com.example.lab193050.model.enumerations.Category;
import lombok.Data;

@Data
public class BookDto {

    private String name;

    private Category category;

    Long author;

    Integer availableCopies;

    public BookDto() {
    }

    public BookDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}

