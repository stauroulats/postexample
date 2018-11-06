package com.stavroula.postexample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.repository.CarRepository;

@Service("carService")
public class CarServiceImpl {
	
	@Autowired
	CarRepository carRepository;
	
	public List<Car> getAllCars(){
		return carRepository.findAll();
	}
	
	public List<Car> getAllCarsByDriver(Long driverId){
		return carRepository.findByDriver(driverId);
	
	}
	
	public Car saveCar(String jsonManufacture, String jsonModel, Driver jsonDriver) {
		Car carReply = new Car();
		carReply.setManufacture(jsonManufacture);
		carReply.setModel(jsonModel);
		carReply.setOwnerDriver(jsonDriver);
		carReply = carRepository.saveAndFlush(carReply);
		return carReply;
		
	}

}
