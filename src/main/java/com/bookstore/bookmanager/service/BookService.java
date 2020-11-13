package com.bookstore.bookmanager.service;

import com.bookstore.bookmanager.model.Book;
import com.bookstore.bookmanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService
{
    private final BookRepository bookRepository;

    @Autowired
    public BookService( BookRepository bookRepository )
    {
        this.bookRepository = bookRepository;
    }

    public Book getBook( Long bookId )
    {
        Optional<Book> book = bookRepository.findById( bookId );

        return book.orElse( null );

        // throw a not found exception
    }

    public List<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }

    public Book createBook( Book book )
    {
        book.setDatePosted( new Date().toString() );

        return bookRepository.save( book );
    }

    public Book updateBook( Book book )
    {
        return bookRepository.save( book );
    }
}
