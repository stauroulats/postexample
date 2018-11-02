package com.stavroula.postexample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToOne;

@Entity
public class RiderReview {

	@Id
	@GeneratedValue
	private Long id;
	private String description;
	private Integer stars;
	
	@OneToOne
	private Trip trip;
	
	@OneToOne
	private RiderRequest riderRequest;
	
	public RiderReview() {
		super();
	}
	
	public RiderReview(String description, Integer stars, Trip trip, RiderRequest riderRequest) {
		this.description = description;
		this.stars = stars;
		this.trip = trip;
		this.riderRequest = riderRequest;
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
