package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {


    @Autowired
    private BookService bookService;

    @GetMapping("/manage-books.html")
    public ModelAndView manageBooks(){
        ModelAndView model = new ModelAndView();
        model.setViewName("manage-books");
        return model;
    }

    @GetMapping("/add-book.html")
    public ModelAndView addBookPage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("add-book");
        return model;
    }

    @PostMapping("/books")
    public ResponseEntity<Map<String,String>> addBook(@RequestBody Book book){

        System.out.println(book);
        Map<String,String> response = new HashMap<>();
        response.put("message", " Book is added");

        bookService.addBook(book);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/books")
    public List<Book> getBooks(
            @RequestParam(required = false) String bookId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher) {


        if (bookId != null) {
            return bookService.getBookById(Long.parseLong(bookId));
        } else if (title != null) {
            return bookService.getBooksByName(title);
        } else if (author != null) {
            return bookService.getBooksByAuthor(author);
        } else if (publisher != null) {
            return bookService.getBooksByPublisher(publisher);
        } else {
            return bookService.getBooks(); // Return all books if no criteria is specified
        }
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Map<String,String>> deleteBook(@PathVariable("bookId") String id){
        bookService.deleteBook(Long.parseLong(id));
        Map<String,String> response = new HashMap<>();
        response.put("message","Book is removed");
        return ResponseEntity.ok(response);
    }


    @PutMapping("/books")
    public ResponseEntity<Map<String,String>> updateBook(@RequestBody Book book){

        Map<String,String> response = new HashMap<>();
        response.put("message", " Book is updated");

        bookService.updateBook(book);
        return ResponseEntity.ok(response);
    }
}
