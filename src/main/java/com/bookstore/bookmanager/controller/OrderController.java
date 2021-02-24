package com.bookstore.bookmanager.controller;

import com.bookstore.bookmanager.model.Book;
import com.bookstore.bookmanager.model.Order;
import com.bookstore.bookmanager.model.OrderStatus;
import com.bookstore.bookmanager.model.User;
import com.bookstore.bookmanager.service.BookService;
import com.bookstore.bookmanager.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin( origins = "http://localhost:3000" )
@RequestMapping( "orders" )
public class OrderController
{
    private OrderService orderService;

    private BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger( OrderController.class );

    @Autowired
    public OrderController( OrderService orderService, BookService bookService )
    {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<?> createOrder( @RequestParam Long bookId)
    {
        Book book = bookService.getBook( bookId );

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        Order order = new Order( OrderStatus.PENDING, book, user );

        return new ResponseEntity<>( orderService.saveOrder( book, user ), HttpStatus.OK );
    }
}
