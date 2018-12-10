package com.stavroula.postexample.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@OneToOne (fetch = FetchType.LAZY,
    cascade =  CascadeType.ALL,
    mappedBy = "riderReview")
	private Trip trip;
	
	public RiderReview() {
		super();
	}
	
	public RiderReview(String description, Integer stars, Trip trip) {
		this.description = description;
		this.stars = stars;
		this.trip = trip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
