package com.stavroula.postexample.service;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.RiderRequest;
import com.stavroula.postexample.entity.Trip;

@Service
public interface TripService {
	
	public Trip getTrip(Long tripId);
	public Trip startTrip(RiderRequest riderRequest);
	public Trip endTrip(Trip trip);
	public Trip payment(Trip trip, String type);


}
