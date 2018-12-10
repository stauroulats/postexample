
package com.stavroula.postexample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.User;

@Service
public interface UserService{
	
	public User getUser(Long userId);
	public List<User> getAllUsers();
    public  User saveRider(String name, String password, String email);
    public  User saveDriver(String name, String password, String email);
    public  User login(String email, String password);
	public void updateUser(User user);
    
}