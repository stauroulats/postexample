package com.stavroula.postexample.service;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.RiderReview;
import com.stavroula.postexample.entity.Trip;

@Service
public interface RiderReviewService {

	public RiderReview saveReview(Integer stars, String description, Trip trip);
}
