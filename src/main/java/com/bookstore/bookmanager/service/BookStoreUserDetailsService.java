package com.bookstore.bookmanager.service;

import com.bookstore.bookmanager.model.User;
import com.bookstore.bookmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class BookStoreUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException
    {
        return userRepository.findByUsername( username )
                       .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found."));
    }

    public Boolean existsByUsername(String username)
    {
        return userRepository.existsByUsername( username );
    }

    public Boolean existsByEmail(String email)
    {
        return userRepository.existsByEmail( email );
    }

    public UserDetails saveUser( User newUser )
    {
        return userRepository.save( newUser );
    }
}
