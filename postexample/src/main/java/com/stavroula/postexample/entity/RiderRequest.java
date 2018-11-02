package com.stavroula.postexample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class RiderRequest {
	
	@Id
	@GeneratedValue
	private Long id;
	private String pickUpPoint;
	private String destination;
	private boolean approved;
	
	@ManyToOne
	private Rider rider;
	
	@ManyToOne
	private Driver driver;
	
	@OneToOne
	private RiderReview riderReview;
	
	public RiderRequest(){
		super();
	}
	
	public RiderRequest(String pickUpPoint, String destination, boolean approved, Rider rider,Driver driver,
			RiderReview riderReview) {
		super();
		this.pickUpPoint = pickUpPoint;
		this.destination = destination;
		this.approved = approved;
		this.rider = rider;
		this.driver = driver;
		this.riderReview = riderReview;
	}

	public String getPickUpPoint() {
		return pickUpPoint;
	}

	public void setPickUpPoint(String pickUpPoint) {
		this.pickUpPoint = pickUpPoint;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
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

	public RiderReview getRiderReview() {
		return riderReview;
	}

	public void setRiderReview(RiderReview riderReview) {
		this.riderReview = riderReview;
	}
	
	

}
