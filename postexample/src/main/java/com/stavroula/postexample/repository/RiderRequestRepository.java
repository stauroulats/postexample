package com.stavroula.postexample.repository;

import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.RiderRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository("riderRequestRepository")
public interface RiderRequestRepository extends JpaRepository<RiderRequest, Long>{
	 
	@Query("SELECT r FROM RiderRequest r WHERE status = 'pending'")
	public List<RiderRequest> findAllPendingRequests();
	

}
