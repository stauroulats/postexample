package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.entity.Status;
import com.stavroula.postexample.entity.TripRequest;
import com.stavroula.postexample.repository.TripRequestRepository;


@Service("riderRequestService")
public class TripRequestServiceImpl implements TripRequestService{
	
	@Autowired
	TripRequestRepository tripRequestRepository;
	
	public TripRequest getTripRequest(Long tripRequestId) {
		Optional<TripRequest> optinalEntity =  tripRequestRepository.findById(tripRequestId);
		TripRequest tripRequest = optinalEntity.isPresent() ? optinalEntity.get():null;
		return tripRequest;
	}
	
	public List<TripRequest> getAllPendingRequests(){
	return tripRequestRepository.findAllPendingRequests();
	}
	
	public TripRequest createRequest(Rider rider, String pickUpPoint, String destination , Long rideDistance) {
		TripRequest tripRequest = new TripRequest();
		tripRequest.setRider(rider);
		tripRequest.setPickUpPoint(pickUpPoint);
		tripRequest.setDestination(destination);
		tripRequest.setRideDistance(rideDistance);
		tripRequest.setStatus(Status.pending);
		tripRequest = tripRequestRepository.saveAndFlush(tripRequest);
		return tripRequest;
	}
	
	public TripRequest acceptRequest(Driver driver, TripRequest tripRequest) {
		tripRequest.setDriver(driver);
		tripRequest.setStatus(Status.approved);
	    tripRequestRepository.saveAndFlush(tripRequest);//void sta saveandflush?
		return tripRequest;
	}
	
	public TripRequest cancelRequest(Long tripRequestId) {
		TripRequest tripRequest = getTripRequest(tripRequestId);
		tripRequest.setStatus(Status.cancelled);
		tripRequest.setDriver(null);//Otan kanei cancel o rider na tin svinei apo ti lista?(des screenshot)
		tripRequestRepository.saveAndFlush(tripRequest);
		return tripRequest;
	}

}
