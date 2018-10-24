
package com.stavroula.postexample.service;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.User;

@Service
public interface UserService{
	
	
    public  User saveUser(String name, String password, String email);
    public  User login(String email, String password);
    
}