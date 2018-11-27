package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Driver;

@Service
public interface DriverService {

	public List<Driver> getAllDrivers();
	
	public Driver getDriver(Long driverId);
	

}
