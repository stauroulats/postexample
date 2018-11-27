package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.entity.RiderReview;

@Service
public interface RiderService {
	
	public List<Rider> getAllRiders();
	
	public Rider getRider(Long riderId);
	

}
