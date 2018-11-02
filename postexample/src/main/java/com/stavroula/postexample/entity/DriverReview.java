package com.stavroula.postexample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DriverReview {
	
	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private Integer stars;
	
	@OneToOne
	private Trip trip;
	
	public DriverReview() {
		super();
	}
	
	public DriverReview(String description, Integer stars, Trip trip) {
		this.description = description;
		this.stars = stars;
		this.trip = trip;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	

}
