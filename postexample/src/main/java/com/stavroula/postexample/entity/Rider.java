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

@Table(name = "rider")
@Entity
public class Rider {
	
	@Id
	@GeneratedValue
	
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
	private User user;
	
	@OneToMany(mappedBy="rider", cascade=CascadeType.ALL)
	private Set<Trip> trips = new HashSet<Trip>();
	
	@OneToMany(mappedBy="rider",orphanRemoval = true, cascade=CascadeType.ALL)
	private Set<TripRequest> tripRequests = new HashSet<TripRequest>();
	
	@OneToMany(mappedBy="rider", cascade=CascadeType.ALL)
	private Set<Appointment> appointments = new HashSet<Appointment>();
	
	@OneToMany(mappedBy="rider", orphanRemoval = true, cascade=CascadeType.ALL)
	private Set<CreditCard> creditCards = new HashSet<CreditCard>();
	
	public Rider() {
		super();
	}
	
	public Rider( Set<Trip> trips, Set<TripRequest> tripRequests, 
			Set<Appointment> appointments ,Set<CreditCard> creditCards) {
		super();
		//this.user = user;
		this.trips = trips;
		this.tripRequests = tripRequests;
		this.appointments = appointments;
		this.creditCards = creditCards;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
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

	public Set<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(Set<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public void addCreditCard(CreditCard creditCard) {
        this.creditCards.add(creditCard);
        creditCard.setRider(this);
    }
	
	public int totalReviews(){
		int countStars = 0;
		for (Trip t : trips) {// In case of no stars?
		countStars = t.getRiderReview().getStars();
		countStars++;
		}
		int totalStars = countStars/trips.size();
		return totalStars;
	}
	

}
