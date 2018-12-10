package com.stavroula.postexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stavroula.postexample.entity.TripRequest;
import com.stavroula.postexample.service.TripRequestService;


@RestController
@RequestMapping("/requests")
public class TripRequestController {
	
	@Autowired
	TripRequestService tripRequestService;
	
	@RequestMapping(value = "/{tripRequestId}" ,method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<TripRequest> getTripRequest(@PathVariable Long tripRequestId){
		TripRequest tripRequest = tripRequestService.getTripRequest(tripRequestId);
		return ResponseEntity.accepted().body(tripRequest);
	}
	
	@RequestMapping(value = "/pending" , method = RequestMethod.GET)
	public ResponseEntity <List<TripRequest>> getPentingRequests(){
		List<TripRequest> pendingRequests = tripRequestService.getAllPendingRequests();
		return ResponseEntity.accepted().body(pendingRequests);
		
		
	}

}
