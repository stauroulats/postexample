package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.repository.CarRepository;
import com.stavroula.postexample.repository.DriverRepository;

@Service("carService")
public class CarServiceImpl implements CarService {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	DriverRepository driverRepository;
	
	public List<Car> getCars(){
		return carRepository.findAll();
	}
	
	public List<Car> getAllCars(Driver driver){
		return carRepository.findCarsByDriverId(driver);
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
		driver.addCar(carReply);
		driverRepository.saveAndFlush(driver);
		return carReply;
		
		}
		
	public void deleteCar(Long carId) {
		carRepository.deleteById(carId);
	}

}
