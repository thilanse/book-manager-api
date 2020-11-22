package com.bookstore.bookmanager.controller;

import com.bookstore.bookmanager.model.Book;
import com.bookstore.bookmanager.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController
{
    private final SearchService searchService;

    @Autowired
    public SearchController( SearchService searchService )
    {
        this.searchService = searchService;
    }

    @GetMapping("search")
    public ResponseEntity<List<Book>> searchBooks( @RequestParam String query )
    {
        return new ResponseEntity<>( searchService.searchBooks(query), HttpStatus.OK );
    }
}
