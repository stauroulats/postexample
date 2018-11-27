 package com.stavroula.postexample.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.CreditCard;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.DriverReview;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.entity.RiderRequest;
import com.stavroula.postexample.entity.RiderRequest.Status;
import com.stavroula.postexample.entity.Trip;
import com.stavroula.postexample.service.CreditCardService;
import com.stavroula.postexample.service.DriverReviewService;
import com.stavroula.postexample.service.RiderRequestService;
import com.stavroula.postexample.service.RiderService;
import com.stavroula.postexample.service.TripService;

@RestController
@RequestMapping("/riders")
public class RiderController {
	
	@Autowired
	RiderService riderService;
	RiderRequestService riderRequestService;
	TripService tripService;
	DriverReviewService driverReviewService;
	CreditCardService creditCardService;
	
	@RequestMapping(value = "" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Rider>> getRiders(){
		List<Rider> riders = riderService.getAllRiders();
		return ResponseEntity.accepted().body(riders);
	}
	
	//CREDITCARD
	@RequestMapping(value = "/{riderId}/creditCards", method = RequestMethod.GET)
	public ResponseEntity<List<CreditCard>> getAllCreditCards(@PathVariable Long riderId){
		Rider rider = riderService.getRider(riderId);
		List<CreditCard> creditCards = (List<CreditCard>) rider.getCreditCard();//Lathos?Prepei apo to repository?
		return ResponseEntity.accepted().body(creditCards);
	}
	
	@RequestMapping(value = "/{riderId}/creditCard/{creditCardId}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<CreditCard> getCreditCard(@PathVariable Long riderId, @PathVariable Long creditCardId){
		Rider rider = (Rider) riderService.getRider(riderId);
		CreditCard creditCard = creditCardService.getCreditCard(creditCardId);
		return ResponseEntity.accepted().body(creditCard);	
	}
	
	@RequestMapping(value = "/{riderIdId}/creditCard" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<CreditCard> createCreditCard(@RequestBody String jsonStr, @PathVariable Long riderId){
		CreditCard jsonObject = new Gson().fromJson(jsonStr, CreditCard.class);
		
		String name = jsonObject.getName();
		Long cardNumber = jsonObject.getCardNumber();
		
		Rider rider = riderService.getRider(riderId);
	
		CreditCard newCreditCard = creditCardService.saveCreditCard(cardNumber, name, rider);//prepei na kanw kai set to repository tou rider?
		return  ResponseEntity.accepted().body(newCreditCard);
	}
	
	//REQUESTS
	@RequestMapping(value="/{riderId}/requests" , method = RequestMethod.POST)
	public ResponseEntity<RiderRequest> createRequest(@RequestBody String jsonStr , @PathVariable Long riderId){
		
		RiderRequest jsonObject = new Gson().fromJson(jsonStr, RiderRequest.class);
		
		String pickUpPoint = jsonObject.getPickUpPoint();
		String destination = jsonObject.getDestination();
		Long rideDistance = jsonObject.getRideDistance();
		
		Rider rider = riderService.getRider(riderId);
		//TODO if rider doesn't exist return error
		
		RiderRequest riderRequest = riderRequestService.createRequest(rider, pickUpPoint , destination, rideDistance);
		return ResponseEntity.accepted().body(riderRequest);
	}
	
	@RequestMapping(value = "/{riderId}/cancelRequest/{riderRequestId}" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<RiderRequest> cancelRequest(@PathVariable Long riderRequestId){
		RiderRequest riderRequest = riderRequestService.cancelRequest(riderRequestId); 
		return ResponseEntity.accepted().body(riderRequest);
	}
	
	
	///REVIEWS
	@RequestMapping(value = "/{riderId}/trip/{tripId}/review", method = RequestMethod.POST)
	public ResponseEntity<DriverReview> review(@RequestBody String jsonStr, @PathVariable Long tripId){
		DriverReview jsonObject = new Gson().fromJson(jsonStr, DriverReview.class);
		Integer stars = jsonObject.getStars();
		String description = jsonObject.getDescription();
		Trip trip = tripService.getTrip(tripId);
		DriverReview driverReview = driverReviewService.saveReview(stars,description,trip);
		trip.setDriverReview(driverReview);
		return ResponseEntity.accepted().body(driverReview);
	}
	
	@RequestMapping(value = "/{riderId}/trips" , method = RequestMethod.GET)
	public ResponseEntity<List<Trip>> getAllTrips(@PathVariable Long riderId){
		Rider rider = riderService.getRider(riderId);
		List<Trip> trips = (List<Trip>) rider.getTrips(); //prepei na ta travixw apo repository kalitera?
		return ResponseEntity.accepted().body(trips);
	}
}

