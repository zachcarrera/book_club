package com.zachcarrera.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zachcarrera.bookclub.models.Book;
import com.zachcarrera.bookclub.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // ----- FIND ALL -----
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    // ----- FIND ONE -----
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        }
        return null;
    }

    // ----- CREATE -----
    public Book createBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    // ----- UPDATE -----
    public Book updateBook(Book oneBook) {
        return bookRepository.save(oneBook);
    }

    // ----- DELETE -----
    public void removeBook(Long id) {
        bookRepository.deleteById(id);
    }
}
