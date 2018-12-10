package com.stavroula.postexample.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.TripRequest;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.DriverReview;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.entity.RiderReview;
import com.stavroula.postexample.entity.Trip;

@Service
public interface TripService {
	
	public Trip getTrip(Long tripId);
	public List<Trip> getAllTrips(Rider rider);
	public List<Trip> getAllTrips(Driver driver);
	public List<Trip> getTripsByDate(Date creationDateTime);
	public Trip startTrip(TripRequest tripRequest);
	public Trip endTrip(Trip trip);
	public Trip payment(Trip trip, String type);
	public void saveRiderReview(Trip trip, RiderReview riderReview);
	public void saveDriverReview(Trip trip, DriverReview driverReview);

}
