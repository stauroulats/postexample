package com.stavroula.postexample.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
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
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.DriverReview;
import com.stavroula.postexample.entity.RiderRequest;
import com.stavroula.postexample.entity.RiderRequest.Status;
import com.stavroula.postexample.entity.RiderReview;
import com.stavroula.postexample.entity.Trip;
import com.stavroula.postexample.repository.CarRepository;
import com.stavroula.postexample.service.CarService;
import com.stavroula.postexample.service.DriverService;
import com.stavroula.postexample.service.RiderRequestService;
import com.stavroula.postexample.service.RiderReviewService;
import com.stavroula.postexample.service.TripService;

@RestController
@RequestMapping("/drivers")
public class DriverController {
	
	@Autowired
	DriverService driverService;
	CarService carService;
	RiderRequestService riderRequestService;
	TripService tripService;
	RiderReviewService riderReviewService;
	
	@RequestMapping(value = "" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Driver>> getDrivers(){
		List<Driver> drivers = driverService.getAllDrivers();
		return ResponseEntity.accepted().body(drivers);
	}
	
	@RequestMapping(value = "/{driverId}" ,method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Driver> getDriver(@PathVariable Long driverId){
		Driver driver = driverService.getDriver(driverId);
		return ResponseEntity.accepted().body(driver);
	}
	
	@RequestMapping(value = "/{driverId}/car" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Car>> getCars(@PathVariable Long driverId){
		Driver driver = (Driver) driverService.getDriver(driverId);
		List<Car> cars = (List<Car>) driver.getCars();
		return ResponseEntity.accepted().body(cars);
		
	}
	
	@RequestMapping(value = "/{driverId}/car/{carId}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Car> getCar(@PathVariable Long driverId, @PathVariable Long carId){
		Driver driver = (Driver) driverService.getDriver(driverId);
		Car car = carService.getCar(carId);
		return ResponseEntity.accepted().body(car);	
	}
	
	
	@RequestMapping(value = "/{driverId}/car" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Car> createCar(@RequestBody String jsonStr, @PathVariable Long driverId){
		Car jsonObject = new Gson().fromJson(jsonStr, Car.class);
		
		String manufacture = jsonObject.getManufacture();
		String model = jsonObject.getModel();
		
		Driver driver = driverService.getDriver(driverId);
	
		Car newCar = carService.saveCar(manufacture, model, driver);
		return  ResponseEntity.accepted().body(newCar);
	}
	
	//REQUESTS
	@RequestMapping(value = "/{driverId}/acceptRequest/{riderRequestId}" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<RiderRequest> acceptRequest(@PathVariable Long driverId, @PathVariable Long riderRequestId){
		Driver driver = driverService.getDriver(driverId);
		RiderRequest riderRequest =(RiderRequest) riderRequestService.getRiderRequest(riderRequestId);
		if( (riderRequest.getStatus() == Status.pending) ){
			riderRequest.setDriver(driver);
			riderRequest.setStatus(Status.approved);
		}
		return ResponseEntity.accepted().body(riderRequest);
	}
	
	@RequestMapping(value = "/{driverId}/cancelRequest/{riderRequestId}" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<RiderRequest> cancelRequest(@PathVariable Long driverRequestId){
		RiderRequest riderRequest = riderRequestService.cancelRequest(driverRequestId); 
		return ResponseEntity.accepted().body(riderRequest);
	}
	
	//TRIPS
	@RequestMapping(value = "/{driverId}/acceptRequest/{riderRequestId}/startTrip" , method = RequestMethod.POST)
	public ResponseEntity<Trip> createTrip(@PathVariable Long riderRequestId){
		RiderRequest riderRequest =(RiderRequest) riderRequestService.getRiderRequest(riderRequestId);
		Trip trip = tripService.startTrip(riderRequest);
		return ResponseEntity.accepted().body(trip);
	}
	
	//REVIEWS
	@RequestMapping(value = "/{driverId}/trip/{tripId}/review", method = RequestMethod.POST)
	public ResponseEntity<RiderReview> review(@RequestBody String jsonStr, @PathVariable Long tripId){
		RiderReview jsonObject = new Gson().fromJson(jsonStr, RiderReview.class);
		Integer stars = jsonObject.getStars();
		String description = jsonObject.getDescription();
		Trip trip = tripService.getTrip(tripId);
		
		RiderReview riderReview = riderReviewService.saveReview(stars,description,trip);
		trip.setRiderReview(riderReview);
		return ResponseEntity.accepted().body(riderReview);
	}
	
}
