package com.stavroula.postexample.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.TripRequest;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.DriverReview;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.entity.RiderReview;
import com.stavroula.postexample.entity.Status;
import com.stavroula.postexample.entity.Trip;
import com.stavroula.postexample.entity.Trip.PaymentMethod;
import com.stavroula.postexample.repository.TripRepository;

@Service("tripService")
public class TripServiceImpl implements TripService {
	
	@Autowired
	TripRepository tripRepository;
	
	public Trip getTrip(Long tripId) {
		Optional<Trip> optionalEntity =  tripRepository.findById(tripId);
		Trip trip = optionalEntity.isPresent() ? optionalEntity.get():null;
		return trip;
	}
	
	public List<Trip> getAllTrips(Rider rider){
		List<Trip> trips = new ArrayList<Trip>();
		java.util.Set<TripRequest> requests = rider.getTripRequests();
		List<TripRequest> tripRequests = new ArrayList<TripRequest>(requests);
		for (TripRequest tripRequest : tripRequests) {
		trips.add(tripRepository.findTripsByTripRequest(tripRequest));
		}
		return trips;
	}
	
	public List<Trip> getAllTrips(Driver driver){
		List<Trip> trips = new ArrayList<Trip>();
		java.util.Set<TripRequest> requests = driver.getTripRequests();
		List<TripRequest> tripRequests = new ArrayList<TripRequest>(requests);
		for (TripRequest tripRequest : tripRequests) {
		trips.add(tripRepository.findTripsByTripRequest(tripRequest));
		}
		return trips;
	}
	
	public List<Trip> getTripsByDate(Date creationDateTime) {
		List<Trip> trips = tripRepository.findAllTripsByDate(creationDateTime);
		return trips;
	}
	
	public Trip startTrip(TripRequest tripRequest){
		Trip trip = new Trip();
		Date date = new Date();
		trip.setTripRequest(tripRequest);
	//	trip.setPickUpPoint(tripRequest.getPickUpPoint());//Tha ta travaei apo to TripRequest
	//	trip.setDestination(tripRequest.getDestination());
		trip.setDate(date);
		trip.setRideDistance(tripRequest.getRideDistance());
		trip.setStatus(Status.inProgress);
		trip.setDriver(tripRequest.getDriver());
		trip.setRider(tripRequest.getRider());
		trip = tripRepository.saveAndFlush(trip);
		return trip;
	}

	public Trip endTrip(Trip trip) {
		//int minutes = trip.getDate().getMinutes(); Calendar efoson exei dimiourgithei date sto starTrip na tsekarw an douleuei to getminutes gia to timeTaken
		trip.setStatus(Status.completed);
		trip = tripRepository.saveAndFlush(trip);
		return trip;
	}
	
	public Trip payment(Trip trip, String type) {
		if (type.equals("cash")) {
			trip.setPaymentMethod(PaymentMethod.cash);}
		else if (type.equals("creditCard")){
			trip.setPaymentMethod(PaymentMethod.creditCard);}
			trip = tripRepository.saveAndFlush(trip);
			return trip;
		}
	
	public void saveRiderReview(Trip trip, RiderReview riderReview) {
		trip.setRiderReview(riderReview);
		tripRepository.saveAndFlush(trip);
	}
	
	public void saveDriverReview(Trip trip, DriverReview driverReview) {
		trip.setDriverReview(driverReview);
		tripRepository.saveAndFlush(trip);
	}
	
	
}
