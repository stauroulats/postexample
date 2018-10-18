
package com.stavroula.postexample.service;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.User;

@Service
public interface UserService{
	
	
    public  User saveUser(User user);
}