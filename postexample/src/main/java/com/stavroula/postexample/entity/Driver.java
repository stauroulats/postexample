package com.stavroula.postexample.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "driver")
public class Driver {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy="driver",cascade=CascadeType.ALL)
	private Set<Trip> trips = new HashSet<Trip>();
	
	@OneToMany(mappedBy="driver", cascade=CascadeType.ALL)
	private Set<TripRequest> tripRequests = new HashSet<TripRequest>();
	
	@OneToMany(mappedBy="driver", cascade=CascadeType.ALL)
	private Set<Appointment> appointments = new HashSet<Appointment>();
	
	@OneToMany(mappedBy="ownerDriver", orphanRemoval = true, cascade=CascadeType.ALL)
	private Set<Car> cars = new HashSet<Car>();
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
	private Car currentCar;

	public Driver() {
		super();
	}
	
	public Driver(Set<Trip> trips, Set<TripRequest> tripRequests, 
			Set<Appointment> appointments ,Set<Car> cars) {
		super();
		//this.user = user;
		this.trips = trips;
		this.tripRequests = tripRequests;
		this.appointments = appointments;
		this.cars = cars;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Trip> getTrips() {
		return trips;
	}

	public void setTrips(Set<Trip> trips) {
		this.trips = trips;
	}

	public Set<TripRequest> getTripRequests() {
		return tripRequests;
	}

	public void setTripRequests(Set<TripRequest> tripRequests) {
		this.tripRequests = tripRequests;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
	
	public void addCar(Car car) {
        this.cars.add(car);
        car.setOwnerDriver(this);
    }
	
	public Car getCurrentCar() {
		return currentCar;
	}

	public void setCurrentCar(Car currentCar) {
		this.currentCar = currentCar;
	}

	public int totalReviews(){
		int countStars = 0;
		for (Trip t : trips) {
		countStars = t.getDriverReview().getStars();
		countStars++;
		}
		int totalStars = countStars/trips.size();
		return totalStars;
	}
	
}
