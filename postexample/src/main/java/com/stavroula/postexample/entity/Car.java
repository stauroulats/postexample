package com.stavroula.postexample.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Car {
	
	@Id
	@GeneratedValue
	private Long id;
	private String manufacture;
	private String model;
	
	/*@OneToMany(mappedBy="car", cascade=CascadeType.ALL)
	private Set<Photo> photos = new HashSet<Photo>();	*/
	
	@ManyToOne
	private Driver driver;
	
	public Car() {
		super();
	}
	
	public Car(String manufacture, String model,Driver driver) {
		super();
		this.manufacture = manufacture;
		this.model =  model;
		/*this.photos = photos;*/
		this.driver = driver;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	/*public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}*/

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	

}
