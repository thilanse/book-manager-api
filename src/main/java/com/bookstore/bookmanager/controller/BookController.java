package com.bookstore.bookmanager.controller;

import com.bookstore.bookmanager.model.Book;
import com.bookstore.bookmanager.model.User;
import com.bookstore.bookmanager.service.BookService;
import com.bookstore.bookmanager.service.BookStoreUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin( origins = "http://localhost:3000" )
@RequestMapping( "books" )
public class BookController
{
    private final BookService bookService;

    @Autowired
    public BookController( BookService bookService )
    {
        this.bookService = bookService;
    }

    @GetMapping( "" )
    public ResponseEntity<List<Book>> getBooks()
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Book> books = bookService.getUserBooks( user.getId() );

        return new ResponseEntity<>( books, HttpStatus.OK );
    }

    @GetMapping( "{bookId}" )
    public ResponseEntity<Book> getBook( @PathVariable Long bookId )
    {
        return new ResponseEntity<>( bookService.getBook( bookId ), HttpStatus.OK );
    }

    @PostMapping( "" )
    public ResponseEntity<Book> createBook( @RequestBody Book book )
    {
        User user = ( User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        book.setUser( user );

        return new ResponseEntity<>( bookService.createBook( book ), HttpStatus.CREATED );
    }

    @PutMapping( "{bookId}" )
    public ResponseEntity<Book> updateBook( @PathVariable Long bookId, @RequestBody Book book )
    {
        return new ResponseEntity<>( bookService.updateBook( book ), HttpStatus.OK );
    }

    @DeleteMapping( "{bookId}" )
    public ResponseEntity<?> deleteBook( @PathVariable Long bookId )
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Book deletedBook = bookService.getBook( bookId );

        if ( deletedBook.getUser().getId().equals( user.getId() ) )
        {
            bookService.deleteBook( bookId );
            return new ResponseEntity<>( deletedBook, HttpStatus.OK );
        }

        return new ResponseEntity<>( "You are not authorized to delete this book", HttpStatus.FORBIDDEN );
    }
}
