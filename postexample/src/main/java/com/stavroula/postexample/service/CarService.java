package com.stavroula.postexample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Driver;

@Service
public interface CarService {
	
	public List<Car> getCars();
	
	public List<Car> getAllCars(Driver driver);
	
	public Car getCar(Long carId);
	
	public Car saveCar(String manufacture, String model, Driver driver);
	
	public void deleteCar(Long carId);


}