package com.stavroula.postexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.RiderRequest;
import com.stavroula.postexample.service.RiderRequestService;


@RestController
@RequestMapping("/requests")
public class RiderRequestController {
	
	@Autowired
	RiderRequestService riderRequestService;
	
	@RequestMapping(value = "/{riderRequestId}" ,method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<RiderRequest> getRiderRequest(@PathVariable Long riderRequestId){
		RiderRequest riderRequest = riderRequestService.getRiderRequest(riderRequestId);
		return ResponseEntity.accepted().body(riderRequest);
	}
	
	@RequestMapping(value = "/pending" , method = RequestMethod.GET)
	public ResponseEntity <List<RiderRequest>> getPentingRequests(){
		List<RiderRequest> pendingRequests = riderRequestService.getAllPendingRequests();
		return ResponseEntity.accepted().body(pendingRequests);
		
		
	}

}
