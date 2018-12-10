package com.stavroula.postexample.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class TripRequest {
	
	@Id
	@GeneratedValue
	private Long id;
	private String pickUpPoint;
	private String destination;
	private Long rideDistance;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="riderId")
	@JsonBackReference
	private Rider rider;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="driverId")
	@JsonBackReference
	private Driver driver;
	
	@OneToOne(fetch = FetchType.LAZY,
    cascade =  CascadeType.ALL,
    mappedBy = "tripRequest",
    optional = true)
	private Trip trip;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
	@JsonIgnore
	private Chat chat;
	
	public TripRequest() {
		// TODO Auto-generated constructor stub
		super();
	}
		
	public TripRequest(String pickUpPoint, String destination, Status status,Long rideDistance, Rider rider, Driver driver) {
		super();
		this.pickUpPoint = pickUpPoint;
		this.destination = destination;
		this.status = status;
		this.rideDistance = rideDistance;
		this.rider = rider;
		this.driver = driver;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public double totalfare() {
		double charge = 0.74;
		double totalfare = (this.rideDistance*charge);
		return totalfare;
	}
}
