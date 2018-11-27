package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.repository.CarRepository;

@Service("carService")
public class CarServiceImpl implements CarService {
	
	@Autowired
	CarRepository carRepository;
	
	public List<Car> getAllCars(){
		return carRepository.findAll();
	}
	
	public Car getCar(Long carId){
		Optional<Car> optinalEntity =  carRepository.findById(carId);
		 Car car = optinalEntity.get();
		return car;
	}
	
	
	public Car saveCar(String manufacture, String model, Driver driver) {
		Car carReply = new Car();
		carReply.setManufacture(manufacture);
		carReply.setModel(model);
		carReply.setOwnerDriver(driver);
		carReply = carRepository.saveAndFlush(carReply);
		return carReply;
		
	}

}
