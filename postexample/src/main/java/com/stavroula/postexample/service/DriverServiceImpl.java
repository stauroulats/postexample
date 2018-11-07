package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.repository.CarRepository;
import com.stavroula.postexample.repository.DriverRepository;

@Service("driverService")
public class DriverServiceImpl implements DriverService {
	
	@Autowired
	DriverRepository driverRepository;
	CarRepository carRepository;
	
	public List<Driver> getAllDrivers() {
		return driverRepository.findAll();
	}
	
	public Optional<Driver> getDriver(Long driverId) {
		return driverRepository.findById(driverId);
	}

}
