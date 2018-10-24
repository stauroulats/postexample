package com.stavroula.postexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.stavroula.postexample.entity.User;
import com.stavroula.postexample.repository.UserRepository;

@SpringBootApplication
public class PostexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostexampleApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner setup(UserRepository userRepository) {
		return (args) -> {
			userRepository.save(new User("James", "Lakes", "james25@gmail.com"));
			userRepository.save(new User("John", "Smith", "johnsmith@yahoo.com"));
			userRepository.save(new User("Jim ", "Morrison", "jimmor@hotmail.com"));
			userRepository.save(new User("David", "Gilmour", "davidg@yahoo.com"));
		};
}
}