package com.stavroula.postexample.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.Trip;
import com.stavroula.postexample.entity.TripRequest;

@Repository("tripRepository")
public interface TripRepository extends JpaRepository<Trip, Long> {

	@Query("SELECT t FROM Trip t WHERE t.date = creationDateTime")
	public List<Trip> findAllTripsByDate(@Param("creationDateTime") Date creationDateTime);
	
	@Query("SELECT t FROM Trip t WHERE t.tripRequest =:tripRequest")
	public Trip findTripsByTripRequest(@Param("tripRequest") TripRequest tripRequest);	
}
