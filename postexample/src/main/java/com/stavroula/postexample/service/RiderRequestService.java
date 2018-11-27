package com.stavroula.postexample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.entity.RiderRequest;


@Service
public interface RiderRequestService {
	
	public RiderRequest getRiderRequest(Long riderRequestId);
	public List<RiderRequest> getAllPendingRequests();
	public RiderRequest createRequest(Rider rider, String pickUpPoint, String destination, Long rideDistance);
	public RiderRequest cancelRequest(Long riderRequestId);
}
