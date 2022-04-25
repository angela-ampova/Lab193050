package com.example.lab193050.repository;

import com.example.lab193050.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    void deleteBookByName(String name);
}
