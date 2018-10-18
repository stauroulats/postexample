package com.stavroula.postexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.User;
import com.stavroula.postexample.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	

	
	public User saveUser(User user) {
		String name = null;
		String password = null;
		String email = null;
		User userReply = new User(name,password,email);
		userReply.setName(user.getName());
		userReply.setEmail(user.getEmail());
		userReply.setPassword(user.getPassword());
		userReply = userRepository.saveAndFlush(user);
		return userReply;
	}


}


