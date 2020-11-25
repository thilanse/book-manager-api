package com.bookstore.bookmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table( name = "users" )
public class User implements UserDetails
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    @NotNull
    @Column( name = "username", unique = true )
    private String username;

    @NotNull
    @Column( name = "email", unique = true )
    private String email;

    @NotNull
    @JsonIgnore
    private String password;

    @OneToMany( mappedBy = "user" )
    @JsonIgnore
    private Set<Book> books;

    @ElementCollection( fetch = FetchType.EAGER )
    private Set<String> roles = new HashSet<>();

    public User()
    {
    }

    public User( String username, String email, String password )
    {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User( String username, String email, String password, Set<String> roles )
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId()
    {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return this.roles.stream().map( SimpleGrantedAuthority::new ).collect( Collectors.toList() );
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }

    public String getEmail()
    {
        return email;
    }

    public Set<Book> getBooks()
    {
        return books;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
