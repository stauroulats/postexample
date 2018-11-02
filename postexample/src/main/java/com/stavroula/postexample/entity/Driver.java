package com.stavroula.postexample.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Driver {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private User user;
	
	@OneToMany(mappedBy="driver",cascade=CascadeType.ALL)
	private Set<Trip> trips = new HashSet<Trip>();
	
	@OneToMany(mappedBy="drider", cascade=CascadeType.ALL)
	private Set<RiderRequest> riderRequests = new HashSet<RiderRequest>();
	
	@OneToMany(mappedBy="drider", cascade=CascadeType.ALL)
	private Set<Appointment> appointments = new HashSet<Appointment>();
	
	@OneToMany(mappedBy="driver", cascade=CascadeType.ALL)
	private Set<Car> cars = new HashSet<Car>();

	public Driver() {
		super();
	}
	
	public Driver(User user, Set<Trip> trips, Set<RiderRequest> riderRequests, 
			Set<Appointment> appointments ,Set<Car> cars) {
		this.user = user;
		this.trips = trips;
		this.riderRequests = riderRequests;
		this.appointments = appointments;
		this.cars = cars;
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

	public Set<RiderRequest> getRiderRequests() {
		return riderRequests;
	}

	public void setRiderRequests(Set<RiderRequest> riderRequests) {
		this.riderRequests = riderRequests;
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
	
	
}
