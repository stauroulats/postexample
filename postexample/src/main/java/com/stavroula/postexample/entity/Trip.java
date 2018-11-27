package com.stavroula.postexample.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.stavroula.postexample.entity.RiderRequest.Status;

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
	//private Date date;
	private String pickUpPoint;
	private String destination;
	//private int stops;
	//private int waitTime;
	//private int timeTaken;
	private int fare;
	private Long rideDistance;
	//enum creditCard/cash
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@ManyToOne
	private Rider rider;
	
	@ManyToOne
	private Driver driver;
	
	@OneToOne
	private RiderReview riderReview;
	
	@OneToOne 
	private DriverReview driverReview;
	
	@OneToOne
	private RiderRequest riderRequest;
	
	public Trip() {
		super();
	}
	
	public Trip(String pickUpPoint, String destination, int fare, Long rideDistance, Rider rider, 
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
	
	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public Rider getRider() {
		return rider;
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

	public void setRider(Rider rider) {
		this.rider = rider;
	}

	
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
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

	public RiderRequest getRiderRequest() {
		return riderRequest;
	}

	public void setRiderRequest(RiderRequest riderRequest) {
		this.riderRequest = riderRequest;
	}
	
	public double totalfare() {
		double charge = 0.74;
		double totalfare = (this.rideDistance*charge);
		return totalfare;
	}
	
}
