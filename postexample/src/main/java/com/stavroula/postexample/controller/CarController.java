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
import com.google.gson.JsonObject;
import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.service.CarService;

@RestController
public class CarController {

	@Autowired 
	CarService carService;

	@RequestMapping(value = "/cars/{driverId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Car>> getCarByDriver(@PathVariable Long driverId) {
		List<Car> carsByDriver = carService.getAllCarsByDriver(driverId);
		return  ResponseEntity.accepted().body(carsByDriver);
		
	}
	
	@RequestMapping(value = "/newCar" , method = RequestMethod.POST)
	public ResponseEntity<Car> createCar(@RequestBody String jsonStr){
		Car jsonObject = new Gson().fromJson(jsonStr, Car.class);
		
		String manufacture = jsonObject.getManufacture();
		String model = jsonObject.getModel();
		Driver driver = (Driver) jsonObject.getOwnerDriver();
		
		Car carReply = carService.saveCar(manufacture, model, driver);
		return  ResponseEntity.accepted().body(carReply);
			
	}
	
		
	}
	