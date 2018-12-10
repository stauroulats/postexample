package com.stavroula.postexample.repository;

import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.TripRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository("tripRequestRepository")
public interface TripRequestRepository extends JpaRepository<TripRequest, Long>{
	 
	@Query("SELECT r FROM TripRequest r WHERE status = 'pending'")
	public List<TripRequest> findAllPendingRequests();
	

}
