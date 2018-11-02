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
public class Rider {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	private User user;
	
	@OneToMany(mappedBy="rider", cascade=CascadeType.ALL)
	private Set<Trip> trips = new HashSet<Trip>();
	
	@OneToMany(mappedBy="rider", cascade=CascadeType.ALL)
	private Set<RiderRequest> riderRequests = new HashSet<RiderRequest>();
	
	@OneToMany(mappedBy="rider", cascade=CascadeType.ALL)
	private Set<Appointment> appointments = new HashSet<Appointment>();
	
	@OneToOne(mappedBy="rider", cascade=CascadeType.ALL)
	private Set<CreditCard> creditCards = new HashSet<CreditCard>();
	
	public Rider() {
		super();
	}
	
	public Rider(User user, Set<Trip> trips, Set<RiderRequest> riderRequests, 
			Set<Appointment> appointments ,Set<CreditCard> creditCards) {
		this.user = user;
		this.trips = trips;
		this.riderRequests = riderRequests;
		this.appointments = appointments;
		this.creditCards = creditCards;
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

	public Set<CreditCard> getCreditCard() {
		return creditCards;
	}

	public void setCreditCards(Set<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}
	
	

}
