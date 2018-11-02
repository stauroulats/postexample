package com.stavroula.postexample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Trip {

	@Id
	@GeneratedValue
	private Long id;
	private String pickUpPoint;
	private String destination;
	
	@ManyToOne
	private Rider rider;
	
	@ManyToOne
	private Driver driver;
	
	@OneToOne
	private RiderReview riderReview;
	
	@OneToOne 
	private DriverReview driverReview;
	
	public Trip() {
		super();
	}
	
	public Trip(String pickUpPoint, String destination, Rider rider, 
			Driver driver, RiderReview riderReview, DriverReview driverReview) {
		super();
		this.pickUpPoint = pickUpPoint;
		this.destination = destination;
		this.rider = rider;
		this.driver = driver;
		this.riderReview = riderReview;
		this.driverReview = driverReview;
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
