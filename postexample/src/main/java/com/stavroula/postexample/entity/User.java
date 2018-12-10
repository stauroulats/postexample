package com.stavroula.postexample.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String password;
	private String email;
	
	
	@OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "photo_id")
	private Photo photo;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user",
            optional = true)
	private Rider rider;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user",
            optional = true)
	private Driver driver;
	
	public User() {
		super();
	}
	
	
	public User(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
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


	public Photo getPhoto() {
		return photo;
	}


	public void setPhoto(Photo photo) {
		this.photo = photo;
	}


	public Rider getRider() {
		return rider;
	}


	public void setRider(Rider rider) {
		this.rider = rider;
	}


	public Driver getDriver() {
		return driver;
	}


	public void setDriver(Driver driver) {
		this.driver = driver;
	}

}
