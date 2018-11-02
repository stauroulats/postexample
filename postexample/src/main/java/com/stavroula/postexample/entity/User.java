package com.stavroula.postexample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String password;
	private String email;
	
	@OneToOne
	private Photo profilePhoto;
	
	@OneToOne
	private Rider rider;
	
	public User() {
		super();
	}
	
	
	public User(String name, String password, String email, Photo profilePhoto, Rider rider) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.profilePhoto = profilePhoto;
		this.rider = rider;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Photo getProfilePhoto() {
		return profilePhoto;
	}


	public void setProfilePhoto(Photo profilePhoto) {
		this.profilePhoto = profilePhoto;
	}


	public Rider getRider() {
		return rider;
	}


	public void setRider(Rider rider) {
		this.rider = rider;
	}
	
	
	

}
