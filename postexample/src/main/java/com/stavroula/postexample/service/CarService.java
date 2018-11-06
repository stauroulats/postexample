package com.stavroula.postexample.service;

import java.util.List;

import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Driver;

public interface CarService {
	
	public List<Car> getAllCars();
	
	public List<Car> getAllCarsByDriver(Long driverId);
	
	public Car saveCar(String manufacture, String model, Driver driver);

}