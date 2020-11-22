package com.bookstore.bookmanager.model;

import org.springframework.security.core.userdetails.UserDetails;

public class LoginResponse
{
    private final String jwt;
    private final UserDetails userDetails;

    public LoginResponse( String jwt, UserDetails userDetails )
    {
        this.jwt = jwt;
        this.userDetails = userDetails;
    }

    public String getJwt()
    {
        return jwt;
    }

    public UserDetails getUserDetails()
    {
        return userDetails;
    }
}
