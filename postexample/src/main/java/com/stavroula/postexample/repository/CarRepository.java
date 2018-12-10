package com.stavroula.postexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Driver;

@Repository("carRepository")
public interface CarRepository extends JpaRepository<Car, Long> {
	
	@Query("select c from Car c where c.ownerDriver =:driver")
	 public List<Car> findCarsByDriverId(@Param("driver") Driver driver);
}
