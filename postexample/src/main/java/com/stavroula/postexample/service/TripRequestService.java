package com.stavroula.postexample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.entity.TripRequest;


@Service
public interface TripRequestService {
	
	public TripRequest getTripRequest(Long tripRequestId);
	public List<TripRequest> getAllPendingRequests();
	public TripRequest createRequest(Rider rider, String pickUpPoint, String destination, Long rideDistance);
	public TripRequest acceptRequest(Driver driver, TripRequest tripRequest);
	public TripRequest cancelRequest(Long riderRequestId);
}
