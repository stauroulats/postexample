package com.stavroula.postexample.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
public class RiderRequest {
	
	public enum Status{
		approved,pending,cancelled
	}
	
	@Id
	@GeneratedValue
	private Long id;
	private String pickUpPoint;
	private String destination;
	private Long rideDistance;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	private Rider rider;
	
	@ManyToOne
	private Driver driver;
	
	@OneToOne
	private Trip trip;
	
	public RiderRequest() {
		// TODO Auto-generated constructor stub
		super();
	}
		
	public RiderRequest(String pickUpPoint, String destination, Status status,Long rideDistance, Rider rider, Driver driver) {
		super();
		this.pickUpPoint = pickUpPoint;
		this.destination = destination;
		this.status = status;
		this.rideDistance = rideDistance;
		this.rider = rider;
		this.driver = driver;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

	public Long getRideDistance() {
		return rideDistance;
	}

	public void setRideDistance(Long rideDistance) {
		this.rideDistance = rideDistance;
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

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}


}
