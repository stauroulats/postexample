package com.stavroula.postexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.Car;

@Repository("carRepository")
public interface CarRepository extends JpaRepository<Car, Long> {
	
	public List<Car> findByDriver(Long driverId);
}
