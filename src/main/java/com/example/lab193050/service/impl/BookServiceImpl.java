package com.example.lab193050.service.impl;

import com.example.lab193050.model.Author;
import com.example.lab193050.model.Book;
import com.example.lab193050.model.dto.BookDto;
import com.example.lab193050.model.enumerations.Category;
import com.example.lab193050.model.exceptions.AuthorNotFoundException;
import com.example.lab193050.model.exceptions.BookNotFoundException;
import com.example.lab193050.repository.AuthorRepository;
import com.example.lab193050.repository.BookRepository;
import com.example.lab193050.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        this.bookRepository.deleteBookByName(bookDto.getName());
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);

    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        book.setName(bookDto.getName());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setCategory(bookDto.getCategory());

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> create(String name, Category category, Long author, Integer avCopies) {
        Author author1 = this.authorRepository.findById(author)
                .orElseThrow(() -> new AuthorNotFoundException(author));
        Book book = new Book(name,category,author1,avCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long author, Integer avCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author1 = this.authorRepository.findById(author)
                .orElseThrow(() -> new AuthorNotFoundException(author));

        book.setName(name);
        book.setAuthor(author1);
        book.setAvailableCopies(avCopies);
        book.setCategory(category);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Book MarkAsTaken(Long id) {
        Optional<Book> book = this.findById(id);
        Integer avCopies = book.get().getAvailableCopies() - 1;
        if(avCopies<=0){
            book.get().setAvailableCopies(0);
        }
        else {book.get().setAvailableCopies(avCopies);}

        return this.bookRepository.save(book.get());
    }
}

