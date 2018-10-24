package com.stavroula.postexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stavroula.postexample.entity.User;
import com.stavroula.postexample.service.UserService;


@RestController
public class UserController {
	
		  
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody String jsonStr){
		JsonObject jsonObject = new Gson().fromJson(jsonStr, JsonObject.class);

		
		String name = jsonObject.get("name").getAsString();
		String password = jsonObject.get("password").getAsString();
		String email = jsonObject.get("email").getAsString();
		
			User userReply = userService.saveUser(name, password, email);
			return  ResponseEntity.accepted().body(userReply);

}
	
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseEntity<User> loginUser(@RequestBody String jsonStr){
	  JsonObject jsonObject = new Gson().fromJson(jsonStr, JsonObject.class);
	  
	  String email = jsonObject.get("email").getAsString();
	  String password = jsonObject.get("password").getAsString();
	  
	  User userReply = userService.login(email, password);
	  
	  if (userReply != null) {
		  return ResponseEntity.ok(userReply);}
	  else {
		  return ResponseEntity.badRequest().body(null);
		  }
  }
}