package com.bookstore.bookmanager.model;

import javax.persistence.*;

@Entity
public class Book
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private String name;
    private String author;
    private String description;
    private String imageUrl;
    private String datePosted;
    private String dateSold;
    private String price;
    private boolean sold;

    public Book()
    {
    }

    public Book( Long id )
    {
        this.id = id;
    }

    public Book( Long id, String name, String author, String description, String imageUrl, String datePosted, String dateSold, String price, boolean sold )
    {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.imageUrl = imageUrl;
        this.datePosted = datePosted;
        this.dateSold = dateSold;
        this.price = price;
        this.sold = sold;
    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor( String author )
    {
        this.author = author;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl( String imageUrl )
    {
        this.imageUrl = imageUrl;
    }

    public String getDatePosted()
    {
        return datePosted;
    }

    public void setDatePosted( String datePosted )
    {
        this.datePosted = datePosted;
    }

    public String getDateSold()
    {
        return dateSold;
    }

    public void setDateSold( String dateSold )
    {
        this.dateSold = dateSold;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice( String price )
    {
        this.price = price;
    }

    public boolean isSold()
    {
        return sold;
    }

    public void setSold( boolean sold )
    {
        this.sold = sold;
    }
}
