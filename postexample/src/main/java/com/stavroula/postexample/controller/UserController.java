package com.stavroula.postexample.controller;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stavroula.postexample.PostexampleApplication;
import com.stavroula.postexample.entity.User;
import com.stavroula.postexample.service.UserService;


@RestController
public class UserController {
	
	public static final org.jboss.logging.Logger logger = LoggerFactory.logger(PostexampleApplication.class);
	  
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<User> createUser(@RequestBody String jsonStr){
		Gson gson = new GsonBuilder().create();
		User u = gson.fromJson(jsonStr, User.class);
	
		logger.info("123", "Creating user", null);
	
			User userReply = userService.saveUser(u);
			return new ResponseEntity<>(userReply, HttpStatus.CREATED);}

}
