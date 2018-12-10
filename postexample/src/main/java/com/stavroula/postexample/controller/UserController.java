package com.stavroula.postexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stavroula.postexample.entity.Photo;
import com.stavroula.postexample.entity.PhotoType;
import com.stavroula.postexample.entity.User;
import com.stavroula.postexample.service.PhotoService;
import com.stavroula.postexample.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	
		  
	@Autowired
	UserService userService;
	
	@Autowired
	PhotoService photoService;
	
	@RequestMapping(value = "" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<User>> getUsers(){
		List<User> users = userService.getAllUsers();
		return ResponseEntity.accepted().body(users);
	}
	
	@RequestMapping(value = "rider/register", method = RequestMethod.POST)
	public ResponseEntity<User> createRider(@RequestBody String jsonStr){
		JsonObject jsonObject = new Gson().fromJson(jsonStr, JsonObject.class);

		String name = jsonObject.get("name").getAsString();
		String password = jsonObject.get("password").getAsString();
		String email = jsonObject.get("email").getAsString();
		
		User userReply = userService.saveRider(name, password, email);
			return  ResponseEntity.accepted().body(userReply);
	}
	
	@RequestMapping(value = "driver/register", method = RequestMethod.POST)
	public ResponseEntity<User> createDriver(@RequestBody String jsonStr){
		JsonObject jsonObject = new Gson().fromJson(jsonStr, JsonObject.class);

		String name = jsonObject.get("name").getAsString();
		String password = jsonObject.get("password").getAsString();
		String email = jsonObject.get("email").getAsString();
		
		User userReply = userService.saveDriver(name, password, email);
			return  ResponseEntity.accepted().body(userReply);
	}
	
	@RequestMapping(value = "/rider/login", method = RequestMethod.POST)
	public ResponseEntity<User> loginRider(@RequestBody String jsonStr){
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
	
	@RequestMapping(value = "/driver/login", method = RequestMethod.POST)
	public ResponseEntity<User> loginDriver(@RequestBody String jsonStr){
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
	
  @RequestMapping(value="/{userId}/photo/{type}", method = RequestMethod.POST)
  public ResponseEntity<Photo> setPhoto(@RequestBody String jsonStr,@PathVariable Long userId,@PathVariable(value = "type") PhotoType photoType){
	  Photo jsonObject = new Gson().fromJson(jsonStr, Photo.class);
	  
	  User user = userService.getUser(userId);
	  String url = jsonObject.getUrl();
	  PhotoType type = photoType;
	  Photo photo = photoService.savePhoto(url,type,user);
	  return  ResponseEntity.accepted().body(photo);
  }
	
 	@RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
 	public ResponseEntity<?> updateUser(@PathVariable("id") long userId, @RequestBody User user) {
     // logger.info("Updating User with id {}", id);

      User currentUser = userService.getUser(userId);

      if (currentUser == null) {
         // logger.error("Unable to update. User with id {} not found.", id);
         // return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
          //        HttpStatus.NOT_FOUND);
      }

      currentUser.setName(user.getName());
      currentUser.setPassword(user.getPassword());
      currentUser.setEmail(user.getEmail());

      userService.updateUser(currentUser);
      return new ResponseEntity<User>(currentUser, HttpStatus.OK);
  }
  
}