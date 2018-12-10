package com.stavroula.postexample.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.stavroula.postexample.entity.Appointment;
import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.TripRequest;
import com.stavroula.postexample.entity.TripRequest.Status;
import com.stavroula.postexample.entity.RiderReview;
import com.stavroula.postexample.entity.Trip;
import com.stavroula.postexample.repository.DriverRepository;
import com.stavroula.postexample.service.AppointmentService;
import com.stavroula.postexample.service.CarService;
import com.stavroula.postexample.service.DriverService;
import com.stavroula.postexample.service.TripRequestService;
import com.stavroula.postexample.service.RiderReviewService;
import com.stavroula.postexample.service.TripService;

@RestController
@RequestMapping("/drivers")
public class DriverController {
	
	@Autowired
	DriverService driverService;
	
	@Autowired
	CarService carService;
	
	@Autowired
	TripRequestService tripRequestService;
	
	@Autowired
	TripService tripService;
	
	@Autowired
	RiderReviewService riderReviewService;
	
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	DriverRepository driverRepository;
	
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
	
	@RequestMapping(value = "/{driverId}/cars" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Car>> getAllCars(@PathVariable Long driverId){
		Driver driver = (Driver) driverService.getDriver(driverId);
		List<Car> cars = carService.getAllCars(driver);
		return ResponseEntity.accepted().body(cars);
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
	
	@RequestMapping(value ="/{driverId}/car/currentCar/{carId}" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Car> selectCar(@PathVariable Long driverId, @PathVariable Long carId){
		Driver driver = driverService.getDriver(driverId);
		Car selectedCar = carService.getCar(carId);
		driverService.selectCar(driver, selectedCar);
		return  ResponseEntity.accepted().body(selectedCar);
	}
	
	//REQUESTS
	@RequestMapping(value = "/{driverId}/acceptRequest/{tripRequestId}" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<TripRequest> acceptRequest(@PathVariable Long driverId, @PathVariable Long tripRequestId){
		Driver driver = driverService.getDriver(driverId);
		TripRequest tripRequest =(TripRequest) tripRequestService.getTripRequest(tripRequestId);
		if( (tripRequest.getStatus() == Status.pending) ){
			tripRequestService.acceptRequest(driver,tripRequest);
		}
		return ResponseEntity.accepted().body(tripRequest);
	}
	
	@RequestMapping(value = "/{driverId}/cancelRequest/{tripRequestId}" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<TripRequest> cancelRequest(@PathVariable Long tripRequestId){
		TripRequest tripRequest = tripRequestService.cancelRequest(tripRequestId); 
		return ResponseEntity.accepted().body(tripRequest);
	}
	
	//APPOINTMENTS
	@RequestMapping(value = "/{driverId}/acceptAppointment/{appointmentId}" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Appointment> acceptAppointment(@PathVariable Long driverId, @PathVariable Long appointmentId){
		Driver driver = driverService.getDriver(driverId);
		Appointment appointment =(Appointment) appointmentService.getAppointment(appointmentId);
		appointmentService.saveAppointment(driver,appointment);
		return ResponseEntity.accepted().body(appointment);
	}
	
	@RequestMapping(value = "/{driverId}/cancelAppointment/{appointmentId}" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Appointment> cancelAppointment(@PathVariable Long appointmentId){
		Appointment appointment = appointmentService.cancelAppointment(appointmentId); 
		return ResponseEntity.accepted().body(appointment);
	}
	
	//TRIPS
	@RequestMapping(value = "/{driverId}/acceptRequest/{tripRequestId}/startTrip" , method = RequestMethod.POST)
	public ResponseEntity<Trip> createTrip(@PathVariable Long tripRequestId){
		TripRequest tripRequest =(TripRequest) tripRequestService.getTripRequest(tripRequestId);
		Trip trip = tripService.startTrip(tripRequest);
		return ResponseEntity.accepted().body(trip);
	}
	
	@RequestMapping(value = "/{driverId}/trips/{date}" , method = RequestMethod.GET)
	public ResponseEntity<List<Trip>> getAllTrips(@PathVariable  @DateTimeFormat(iso=ISO.DATE) Date date){
		List<Trip> trips = tripService.getTripsByDate(date);
		return ResponseEntity.accepted().body(trips);
	}
	
	@RequestMapping(value = "/{driverId}/trips" , method = RequestMethod.GET)
	public ResponseEntity<List<Trip>> getAllTrips(@PathVariable Long riderId){
		Driver driver = driverService.getDriver(riderId);
		List<Trip> trips = tripService.getAllTrips(driver);
		return ResponseEntity.accepted().body(trips);
	}
	
	@RequestMapping(value = "/{driverId}/trip/{tripId}" , method = RequestMethod.GET)
	public ResponseEntity<Trip> getTrip(@PathVariable Long tripId){
		Trip trip =  tripService.getTrip(tripId);
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
		tripService.saveRiderReview(trip,riderReview);
		return ResponseEntity.accepted().body(riderReview);
	}
	
}
