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
import com.stavroula.postexample.entity.CreditCard;
import com.stavroula.postexample.entity.DriverReview;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.entity.TripRequest;
import com.stavroula.postexample.repository.RiderRepository;
import com.stavroula.postexample.entity.Trip;
import com.stavroula.postexample.service.CreditCardService;
import com.stavroula.postexample.service.DriverReviewService;
import com.stavroula.postexample.service.TripRequestService;
import com.stavroula.postexample.service.RiderService;
import com.stavroula.postexample.service.TripService;

@RestController
@RequestMapping("/riders")
public class RiderController {
	
	@Autowired
	RiderService riderService;
	
	@Autowired
	TripRequestService tripRequestService;

	@Autowired
	TripService tripService;
	
	@Autowired
	DriverReviewService driverReviewService;
	
	@Autowired
	CreditCardService creditCardService;
	
	@Autowired
	RiderRepository riderRepository;
	
	@RequestMapping(value = "" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Rider>> getRiders(){
		List<Rider> riders = riderService.getAllRiders();
		return ResponseEntity.accepted().body(riders);
	}
	
	@RequestMapping(value = "/{riderId}" ,method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Rider> getRider(@PathVariable Long riderId){
		Rider rider = riderService.getRider(riderId);
		return ResponseEntity.accepted().body(rider);
	}
	
	//CREDITCARDS
	@RequestMapping(value = "/{riderId}/creditCards", method = RequestMethod.GET)
	public ResponseEntity<List<CreditCard>> getAllCreditCards(@PathVariable Long riderId){
		Rider rider = riderService.getRider(riderId);
		List<CreditCard> creditCards = creditCardService.getAllCreditCards(rider);
		return ResponseEntity.accepted().body(creditCards);
	}
	
	@RequestMapping(value = "/{riderId}/creditCard/{creditCardId}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<CreditCard> getCreditCard(@PathVariable Long creditCardId, @PathVariable Long riderId){
	//	Rider rider = (Rider) riderService.getRider(riderId);
		CreditCard creditCard = (CreditCard) creditCardService.getCreditCard(creditCardId);
		return ResponseEntity.accepted().body(creditCard);	
	}
	
	@RequestMapping(value = "/{riderId}/creditCard" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<CreditCard> createCreditCard(@RequestBody String jsonStr, @PathVariable Long riderId){
		CreditCard jsonObject = new Gson().fromJson(jsonStr, CreditCard.class);
		
		String name = jsonObject.getName();
		Long cardNumber = jsonObject.getCardNumber();
		
		Rider rider = riderService.getRider(riderId);
		
		CreditCard newCreditCard = creditCardService.saveCreditCard(cardNumber, name, rider);
		riderRepository.saveAndFlush(rider);
		return  ResponseEntity.accepted().body(newCreditCard);
	}
	
	//REQUESTS
	@RequestMapping(value="/{riderId}/request" , method = RequestMethod.POST)
	public ResponseEntity<TripRequest> createRequest(@RequestBody String jsonStr , @PathVariable Long riderId){
		
		TripRequest jsonObject = new Gson().fromJson(jsonStr, TripRequest.class);
		
		String pickUpPoint = jsonObject.getPickUpPoint();
		String destination = jsonObject.getDestination();
		Long rideDistance = jsonObject.getRideDistance();
		
		Rider rider = riderService.getRider(riderId);
		//TODO if rider doesn't exist return error
		
		TripRequest tripRequest = tripRequestService.createRequest(rider, pickUpPoint , destination, rideDistance);
		return ResponseEntity.accepted().body(tripRequest);
	}
	
	@RequestMapping(value = "/{riderId}/cancelRequest/{tripRequestId}" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<TripRequest> cancelRequest(@PathVariable Long tripRequestId){
		TripRequest tripRequest = tripRequestService.cancelRequest(tripRequestId); 
		return ResponseEntity.accepted().body(tripRequest);
	}
	
	
	///REVIEWS
	@RequestMapping(value = "/{riderId}/trip/{tripId}/review", method = RequestMethod.POST)
	public ResponseEntity<DriverReview> review(@RequestBody String jsonStr, @PathVariable Long tripId){
		DriverReview jsonObject = new Gson().fromJson(jsonStr, DriverReview.class);
		Integer stars = jsonObject.getStars();
		String description = jsonObject.getDescription();
		Trip trip = tripService.getTrip(tripId);
		DriverReview driverReview = driverReviewService.saveReview(stars,description,trip);
		tripService.saveDriverReview(trip, driverReview);
		return ResponseEntity.accepted().body(driverReview);
	}
	
	//TRIPS
	@RequestMapping(value = "/{riderId}/trips" , method = RequestMethod.GET)
	public ResponseEntity<List<Trip>> getAllTrips(@PathVariable Long riderId){
		Rider rider = riderService.getRider(riderId);
		List<Trip> trips = tripService.getAllTrips(rider);
		return ResponseEntity.accepted().body(trips);
	}
	
	@RequestMapping(value = "/{riderId}/trips/{tripId}" , method = RequestMethod.GET)
	public ResponseEntity<Trip> getTrip(@PathVariable Long tripId){
		Trip trip =  tripService.getTrip(tripId); //prepei na ta travixw apo repository kalitera?
		return ResponseEntity.accepted().body(trip);
	}
}

