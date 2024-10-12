package com.example.service;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {


    @Autowired
    private BookRepository bookRepository;

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getBooks() {
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }


    public List<Book> getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return Arrays.asList(book.get());
        }
        return null;
    }

    public List<Book> getBooksByName(String title) {
        List<Book> bookList = bookRepository.findByTitleContainingIgnoreCase(title);
        return bookList;
    }

    public List<Book> getBooksByAuthor(String author) {
        List<Book> bookList = bookRepository.findByAuthorContainingIgnoreCase(author);
        return bookList;
    }

    public List<Book> getBooksByPublisher(String publisher) {
        List<Book> bookList = bookRepository.findAllByPublisherContainingIgnoreCase(publisher);
        return bookList;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }
}
