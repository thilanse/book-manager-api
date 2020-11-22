package com.bookstore.bookmanager.controller;

import com.bookstore.bookmanager.model.Book;
import com.bookstore.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("books")
public class BookController
{
    private final BookService bookService;

    @Autowired
    public BookController( BookService bookService )
    {
        this.bookService = bookService;
    }

    @GetMapping( "" )
    public ResponseEntity<List<Book>> getAllBooks()
    {
        return new ResponseEntity<>( bookService.getAllBooks(), HttpStatus.OK );
    }

    @GetMapping( "{bookId}" )
    public ResponseEntity<Book> getBook( @PathVariable Long bookId )
    {
        return new ResponseEntity<>( bookService.getBook( bookId ), HttpStatus.OK );
    }

    @PostMapping( "" )
    public ResponseEntity<Book> createBook( @RequestBody Book book )
    {
        return new ResponseEntity<>( bookService.createBook( book ), HttpStatus.CREATED );
    }

    @PutMapping( "{bookId}" )
    public ResponseEntity<Book> updateBook( @PathVariable Long bookId, @RequestBody Book book )
    {
        return new ResponseEntity<>( bookService.updateBook( book ), HttpStatus.OK );
    }

    @DeleteMapping( "{bookId}" )
    public ResponseEntity<Book> deleteBook( @PathVariable Long bookId )
    {
        Book deletedBook = bookService.getBook( bookId );
        bookService.deleteBook( bookId );
        return new ResponseEntity<>( deletedBook, HttpStatus.OK );
    }
}
