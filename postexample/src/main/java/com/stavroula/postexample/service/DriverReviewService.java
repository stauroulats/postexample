package com.stavroula.postexample.service;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.DriverReview;
import com.stavroula.postexample.entity.Trip;

@Service
public interface DriverReviewService {
	
	public DriverReview saveReview(Integer stars, String description, Trip trip);

}
