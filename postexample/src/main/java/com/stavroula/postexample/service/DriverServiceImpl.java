package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.repository.CarRepository;
import com.stavroula.postexample.repository.DriverRepository;

@Service("driverService")
public class DriverServiceImpl implements DriverService {
	
	@Autowired
	DriverRepository driverRepository;
	
	@Autowired
	CarRepository carRepository;
	
	public List<Driver> getAllDrivers() {
		return driverRepository.findAll();
	}
	
	public Driver getDriver(Long driverId) {
		Optional<Driver> optionalEntity =  driverRepository.findById(driverId);
		Driver driver = optionalEntity.isPresent() ? optionalEntity.get():null;
		return driver;
	}
	
	public void selectCar(Driver driver, Car car) {
		driver.setCurrentCar(car);
		driverRepository.saveAndFlush(driver);
	}

}
