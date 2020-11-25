package com.bookstore.bookmanager.controller;

import com.bookstore.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin( origins = "http://localhost:3000" )
@RequestMapping( "recommendations" )
public class RecommendationController
{
    @Autowired
    private BookService bookService;

    @GetMapping("")
    public ResponseEntity<?> getRecommendedBooks()
    {
        return new ResponseEntity<>( bookService.getAllBooks(), HttpStatus.OK );
    }
}
