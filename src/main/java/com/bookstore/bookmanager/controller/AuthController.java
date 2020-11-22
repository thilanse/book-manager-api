package com.bookstore.bookmanager.controller;

import com.bookstore.bookmanager.model.LoginRequest;
import com.bookstore.bookmanager.model.LoginResponse;
import com.bookstore.bookmanager.model.SignupRequest;
import com.bookstore.bookmanager.model.User;
import com.bookstore.bookmanager.service.BookStoreUserDetailsService;
import com.bookstore.bookmanager.support.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BookStoreUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping( "/authenticate" )
    public ResponseEntity<?> createAuthenticationToken( @RequestBody LoginRequest loginRequest ) throws Exception
    {
        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken( loginRequest.getUsername(), loginRequest.getPassword() ) );
        } catch ( BadCredentialsException exception )
        {
            throw new Exception( "Incorrect username or password", exception );
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername( loginRequest.getUsername() );

        String jwt = jwtUtil.generateToken( userDetails );

        return ResponseEntity.ok(new LoginResponse( jwt, userDetails ) );
    }

    @PostMapping("signup")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest )
    {
        if ( userDetailsService.existsByUsername( signUpRequest.getUsername() ) )
        {
            return ResponseEntity.ok("Account with username '" + signUpRequest.getUsername() + "' already exists");
        }

        if ( userDetailsService.existsByEmail( signUpRequest.getEmail() ) )
        {
            return ResponseEntity.ok("Account with email '" + signUpRequest.getEmail() + "' already exists");
        }

        UserDetails userDetails = userDetailsService.saveUser( new User(signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword()) );

        return ResponseEntity.ok( userDetails);
    }
}
