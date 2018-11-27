package com.stavroula.postexample.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.RiderRequest;
import com.stavroula.postexample.entity.Trip;
import com.stavroula.postexample.entity.Trip.PaymentMethod;
import com.stavroula.postexample.entity.Trip.Status;
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
	
	public Trip startTrip(RiderRequest riderRequest){
		Trip trip = new Trip();
		trip.setPickUpPoint(riderRequest.getDestination());
		trip.setDestination(riderRequest.getDestination());
		trip.setRideDistance(riderRequest.getRideDistance());
		trip.setStatus(Status.inProgress);
		trip.setDriver(riderRequest.getDriver());
		trip.setRider(riderRequest.getRider());
		trip = tripRepository.saveAndFlush(trip);
		return trip;
	}

	public Trip endTrip(Trip trip) {
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
}
