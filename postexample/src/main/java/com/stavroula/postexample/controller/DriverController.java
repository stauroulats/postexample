package com.stavroula.postexample.controller;

import java.util.List;
import java.util.Optional;

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
import com.stavroula.postexample.service.CarService;
import com.stavroula.postexample.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {
	
	@Autowired
	DriverService driverService;
	CarService carService;
	
	@RequestMapping(value = " " , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Driver>> getDrivers(){
		List<Driver> drivers = driverService.getAllDrivers();
		return ResponseEntity.accepted().body(drivers);
	}
	
	@RequestMapping(value = "/{driverId}" ,method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Optional<Driver>> getDriver(@PathVariable Long driverId){
		Optional<Driver> driver = driverService.getDriver(driverId);
		return ResponseEntity.accepted().body(driver);
	}
	
	@RequestMapping(value = "/{driverId}/car" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Car>> getCars(@PathVariable Long driverId){
		Optional<Driver> driver = (Optional<Driver>) driverService.getDriver(driverId);
		List<Car> cars = (List<Car>) driver.get().getCars();
		return ResponseEntity.accepted().body(cars);
		
	}
	
	@RequestMapping(value = "/{driverId}/car" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Car> createCar(@RequestBody String jsonStr, @PathVariable Long driverId){
		Car jsonObject = new Gson().fromJson(jsonStr, Car.class);
		
		String manufacture = jsonObject.getManufacture();
		String model = jsonObject.getModel();
		
		Optional<Driver> driver = driverService.getDriver(driverId);
	
		Car newCar = carService.saveCar(manufacture, model, driver);
		return  ResponseEntity.accepted().body(newCar);
		
	}

}
