package com.bookstore.bookmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties(value= {"user"})
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private OrderStatus orderStatus;

    @OneToOne(targetEntity = Book.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Order()
    {
    }

    public Order( OrderStatus orderStatus, Book book, User user )
    {
        this.orderStatus = orderStatus;
        this.book = book;
        this.user = user;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public OrderStatus getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus( OrderStatus orderStatus )
    {
        this.orderStatus = orderStatus;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook( Book book )
    {
        this.book = book;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser( User user )
    {
        this.user = user;
    }
}
