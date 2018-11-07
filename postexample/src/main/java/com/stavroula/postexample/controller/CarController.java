package com.stavroula.postexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.service.CarService;

@RestController
public class CarController {

	@Autowired 
	CarService carService;
	
	@RequestMapping(value = "/car" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Car>> getAllCars(){
		List<Car> cars = carService.getAllCars();
		return ResponseEntity.accepted().body(cars);
	}
	
		
	}
	