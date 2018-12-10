package com.stavroula.postexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stavroula.postexample.entity.Trip;
import com.stavroula.postexample.service.TripService;

@RestController
@RequestMapping("/trips")
public class TripController {
	
	@Autowired
	TripService tripService;
	
	@RequestMapping(value = "/{tripId}/endTrip" , method = RequestMethod.POST)
	public ResponseEntity<Trip> endTrip(@PathVariable Long tripId){
		Trip trip = tripService.getTrip(tripId);
		trip = tripService.endTrip(trip);
		return ResponseEntity.accepted().body(trip);
	}
	
	@RequestMapping(value = "/{tripId}/payment/{paymentType}" , method = RequestMethod.POST)
	public ResponseEntity<Trip> paymentC(@PathVariable Long tripId, @PathVariable ("paymentType") String paymentType){
		Trip trip = tripService.getTrip(tripId);
		trip = tripService.payment(trip,paymentType);
		return ResponseEntity.accepted().body(trip);
	}

}
