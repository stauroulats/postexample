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
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Trip {

	public enum Status{
		inProgress,completed
	}
	
	public enum PaymentMethod{  //Gia na iparxei sto TripHistory o tropos plirwmis
		cash,creditCard
	}
	
	@Id
	@GeneratedValue
	private Long id;
	private Date date;
	//private int stops;
	//private int waitTime;
	//private int timeTaken;
	//private int fare;
	private Long rideDistance;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="riderId")
	@JsonBackReference
	private Rider rider;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="driverId")
	@JsonBackReference
	private Driver driver;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "riderReview_id")
	@JsonIgnore
	private RiderReview riderReview;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driverReview_id")
	@JsonIgnore
	private DriverReview driverReview;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tripRequest_id")
	@JsonIgnore
	private TripRequest tripRequest;
	
	
	public Trip() {
		super();
	}
	
	public Trip(Long rideDistance,RiderReview riderReview, DriverReview driverReview) {
		super();
		//this.rider = rider;
		//this.driver = driver;
		this.riderReview = riderReview;
		this.driverReview = driverReview;
	}
	
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	/*public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}*/

	
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
	
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
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

	public DriverReview getDriverReview() {
		return driverReview;
	}

	public void setDriverReview(DriverReview driverReview) {
		this.driverReview = driverReview;
	}

	public TripRequest getTripRequest() {
		return tripRequest;
	}

	public void setTripRequest(TripRequest tripRequest) {
		this.tripRequest = tripRequest;
	}
	
	public double totalfare() {
		double charge = 0.74;
		double totalfare = (this.rideDistance*charge);
		return totalfare;
	}
	
	
	
}
