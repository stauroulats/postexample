
package com.stavroula.postexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stavroula.postexample.entity.User;


public interface UserRepository extends JpaRepository<User,Long>{
	
	public User findByEmail(String email);
}