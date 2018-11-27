package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.entity.RiderRequest;
import com.stavroula.postexample.entity.RiderRequest.Status;
import com.stavroula.postexample.repository.RiderRequestRepository;


@Service("riderRequestService")
public class RiderRequestServiceImpl implements RiderRequestService{
	
	@Autowired
	RiderRequestRepository riderRequestRepository;
	
	public RiderRequest getRiderRequest(Long riderRequestId) {
		Optional<RiderRequest> optinalEntity =  riderRequestRepository.findById(riderRequestId);
		RiderRequest riderRequest = optinalEntity.isPresent() ? optinalEntity.get():null;
		return riderRequest;
	}
	
	public List<RiderRequest> getAllPendingRequests(){
	return riderRequestRepository.findAllPendingRequests();
	}
	
	public RiderRequest createRequest(Rider rider, String pickUpPoint, String destination , Long rideDistance) {
		RiderRequest riderRequest = new RiderRequest();
		riderRequest.setRider(rider);
		riderRequest.setPickUpPoint(pickUpPoint);
		riderRequest.setDestination(destination);
		riderRequest.setRideDistance(rideDistance);
		riderRequest.setStatus(Status.pending);
		riderRequest = riderRequestRepository.saveAndFlush(riderRequest);
		return riderRequest;
	}
	
	public RiderRequest cancelRequest(Long riderRequestId) {
		RiderRequest riderRequest = getRiderRequest(riderRequestId);
		riderRequest.setStatus(Status.cancelled);
		return riderRequest;
	}

}
