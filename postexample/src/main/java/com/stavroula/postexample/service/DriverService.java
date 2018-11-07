package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import com.stavroula.postexample.entity.Driver;


public interface DriverService {

	public List<Driver> getAllDrivers();
	
	public Optional<Driver> getDriver(Long driverId);
	

}
