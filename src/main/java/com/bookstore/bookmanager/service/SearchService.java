package com.bookstore.bookmanager.service;

import com.bookstore.bookmanager.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService
{
    private final BookService bookService;

    @Autowired
    public SearchService( BookService bookService )
    {
        this.bookService = bookService;
    }

    public List<Book> searchBooks( String query )
    {
        // Todo Implement proper search functionality
        return bookService.getAllBooks();
    }
}
