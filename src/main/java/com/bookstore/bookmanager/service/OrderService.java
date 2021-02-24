package com.bookstore.bookmanager.service;

import com.bookstore.bookmanager.model.Book;
import com.bookstore.bookmanager.model.Order;
import com.bookstore.bookmanager.model.OrderStatus;
import com.bookstore.bookmanager.model.User;
import com.bookstore.bookmanager.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder( Book book, User user )
    {
        Order order = new Order( OrderStatus.PENDING, book,user );

        return orderRepository.save( order );
    }
}
