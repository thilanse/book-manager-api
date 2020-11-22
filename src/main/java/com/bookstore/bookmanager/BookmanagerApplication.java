package com.bookstore.bookmanager;

import com.bookstore.bookmanager.model.User;
import com.bookstore.bookmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;


@SpringBootApplication
public class BookmanagerApplication implements WebMvcConfigurer, CommandLineRunner
{

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookmanagerApplication.class, args);
	}

	@Override
	public void addViewControllers( ViewControllerRegistry registry )
	{
		registry.addRedirectViewController( "/api", "/swagger-ui.html" );
	}

	@Override
	public void run( String... args ) throws Exception
	{
		User user = new User("user", "user@gmail.com","password", Collections.emptyList());

		if ( userRepository.findByUsername( user.getUsername() ).isEmpty() )
		{
			userRepository.save( user );
		}
	}
}