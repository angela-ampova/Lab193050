package com.example.lab193050.config;

import com.example.lab193050.model.Author;
import com.example.lab193050.model.Book;
import com.example.lab193050.model.Country;
import com.example.lab193050.model.enumerations.Category;
import com.example.lab193050.service.AuthorService;
import com.example.lab193050.service.BookService;
import com.example.lab193050.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.List;

@Component
public class DataInitializer {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public DataInitializer(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void initData(){
        this.countryService.create("C1","Continent1");
        this.countryService.create("C2","Continent2");
        this.countryService.create("C3","Continent3");
        this.countryService.create("C4","Continent4");
        this.countryService.create("C5","Continent5");
        List<Country> countries = this.countryService.listAll();

        this.authorService.create("A1","S1",countries.get(0).getId());
        this.authorService.create("A2","S2",countries.get(1).getId());
        this.authorService.create("A3","S3",countries.get(2).getId());
        this.authorService.create("A4","S4",countries.get(3).getId());
        this.authorService.create("A5","S5",countries.get(4).getId());
        List<Author> authors = this.authorService.listAll();

        this.bookService.create("B1", Category.DRAMA,authors.get(0).getId(),7);
        this.bookService.create("B2",Category.THRILLER,authors.get(1).getId(),12);
        this.bookService.create("B3",Category.BIOGRAPHY,authors.get(2).getId(),0);
        this.bookService.create("B4",Category.CLASSICS,authors.get(3).getId(),5);
        this.bookService.create("B5",Category.NOVEL,authors.get(4).getId(),10);
        this.bookService.create("B6",Category.CLASSICS,authors.get(1).getId(),11);
        this.bookService.create("B7",Category.DRAMA,authors.get(2).getId(),3);
        this.bookService.create("B8",Category.BIOGRAPHY,authors.get(3).getId(),4);
        this.bookService.create("B9",Category.THRILLER,authors.get(4).getId(),3);
        this.bookService.create("B10",Category.HISTORY,authors.get(1).getId(),7);
        this.bookService.create("B11",Category.FANTASY,authors.get(0).getId(),2);
        List<Book> books = this.bookService.listAll();


    }
}

