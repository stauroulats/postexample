package com.stavroula.postexample.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.User;
import com.stavroula.postexample.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
    private static final AtomicLong counter = new AtomicLong();

	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		
		if (user == null)
		 return null;
			
		return user;
	}
	
	public User saveUser(User user) {
		String name = null;
		String password = null;
		String email = null;
		User userReply = new User(name,password,email);
        userReply.setId(counter.incrementAndGet());
		userReply.setName(user.getName());
		userReply.setEmail(user.getEmail());
		userReply.setPassword(user.getPassword());
		return userReply;
	}


}


