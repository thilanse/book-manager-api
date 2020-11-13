package com.bookstore.bookmanager.controller;

import com.bookstore.bookmanager.model.Book;
import com.bookstore.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BookController
{
    private final BookService bookService;

    @Autowired
    public BookController( BookService bookService )
    {
        this.bookService = bookService;
    }

    @GetMapping( "books" )
    public ResponseEntity<List<Book>> getAllBooks()
    {
        return new ResponseEntity<>( bookService.getAllBooks(), HttpStatus.OK );
    }

    @GetMapping( "books/{bookId}" )
    public ResponseEntity<Book> getBook( @PathVariable Long bookId )
    {
        return new ResponseEntity<>( bookService.getBook( bookId ), HttpStatus.OK );
    }

    @PostMapping( "books" )
    public ResponseEntity<Book> createBook( @RequestBody Book book )
    {
        return new ResponseEntity<>( bookService.createBook( book ), HttpStatus.CREATED );
    }

    @PutMapping( "books/{bookId}" )
    public ResponseEntity<Book> updateBook( @PathVariable Long bookId, @RequestBody Book book )
    {
        return new ResponseEntity<>( bookService.updateBook( book ), HttpStatus.OK );
    }
}
