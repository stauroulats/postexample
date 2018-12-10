package com.stavroula.postexample.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.stavroula.postexample.entity.TripRequest.Status;

@Entity
public class Appointment {
	
	public enum Status{
		pending,scheduled,cancelled,completed
	}
	
	@Id
	@GeneratedValue
	private Long id;
	//@Temporal(TemporalType.TIMESTAMP)
	//private Date appointmentCreated;
	
	@Temporal(TemporalType.DATE)
	private Date date;
    @Temporal(TemporalType.TIME)
	private Date time;
	
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
	
	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
	

	public Long getRideDistance() {
		return rideDistance;
	}

	public void setRideDistance(Long rideDistance) {
		this.rideDistance = rideDistance;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double totalfare() {
		double charge = 0.74;
		double totalfare = (this.rideDistance*charge);
		return totalfare;
	}
}
