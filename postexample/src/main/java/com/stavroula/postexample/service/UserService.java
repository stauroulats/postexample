
package com.stavroula.postexample.service;

import com.stavroula.postexample.entity.User;

public interface UserService{
	
	public User findByEmail(String email);
	
    public User saveUser(User user);
}