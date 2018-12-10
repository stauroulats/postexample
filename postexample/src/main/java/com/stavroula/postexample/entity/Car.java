package com.stavroula.postexample.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "car")
public class Car {
	
	@Id
	@GeneratedValue
	private Long id;
	private String manufacture;
	private String model;
	
	@OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "photo_id")
	private Photo photo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ownerDriver_id")
	@JsonBackReference
	private  Driver ownerDriver;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "currentCar"
            )
	private Driver currentDriver;
	
	public Car() {
		super();
	}
	
	public Car(String manufacture, String model,Driver driver) {
		super();
		this.manufacture = manufacture;
		this.model =  model;
		//this.photos = photos;
		this.ownerDriver = driver;
		}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Driver getOwnerDriver() {
		return ownerDriver;
	}

	public void setOwnerDriver(Driver ownerDriver) {
		this.ownerDriver = ownerDriver;
	}
	
}
