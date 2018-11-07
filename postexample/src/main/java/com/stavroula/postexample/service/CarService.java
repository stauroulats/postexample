package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Driver;

public interface CarService {
	
	public List<Car> getAllCars();
	
	public Car saveCar(String manufacture, String model, Optional<Driver> driver);

}