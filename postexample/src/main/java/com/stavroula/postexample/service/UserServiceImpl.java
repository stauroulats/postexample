package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.entity.User;
import com.stavroula.postexample.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User getUser(Long userId) {
		Optional<User> optionalEntity =  userRepository.findById(userId);
		User user = optionalEntity.isPresent() ? optionalEntity.get():null;
		return user;
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User saveRider(String jsonName, String jsonPassword, String jsonEmail) {
		String name = null;
		String password = null;
		String email = null;
		User userReply = new User(name,password,email);
		userReply.setName(jsonName);
		userReply.setEmail(jsonEmail);
		userReply.setPassword(jsonPassword);
		Rider rider = new Rider();
		rider.setUser(userReply);
		userReply.setRider(rider);
		userReply = userRepository.saveAndFlush(userReply);
		return userReply;
	}
	
	public User saveDriver(String jsonName, String jsonPassword, String jsonEmail) {
		String name = null;
		String password = null;
		String email = null;
		User userReply = new User(name,password,email);
		userReply.setName(jsonName);
		userReply.setEmail(jsonEmail);
		userReply.setPassword(jsonPassword);
		Driver driver = new Driver();
		driver.setUser(userReply);
		userReply.setDriver(driver);
		userReply = userRepository.saveAndFlush(userReply);
		return userReply;
	}

	public User login(String jsonEmail, String jsonPassword) {
		User user = userRepository.findByEmail(jsonEmail);
		
		if ((user != null) & user.getPassword().equals(jsonPassword)) {
			return user;}
			else {
				return null;
			}	
		}
	
	public void updateUser(User user) {
		userRepository.saveAndFlush(user);
	}

	}



