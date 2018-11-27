package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Driver;

@Service
public interface CarService {
	
	public List<Car> getAllCars();
	
	public Car getCar(Long carId);
	
	public Car saveCar(String manufacture, String model, Driver driver);


}