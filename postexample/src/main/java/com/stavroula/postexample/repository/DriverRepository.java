package com.stavroula.postexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.Driver;

@Repository("driverRepository")
public interface DriverRepository extends JpaRepository<Driver, Long> {
	
	

}
