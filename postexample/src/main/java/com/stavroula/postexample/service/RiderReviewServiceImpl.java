package com.stavroula.postexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.RiderReview;
import com.stavroula.postexample.entity.Trip;
import com.stavroula.postexample.repository.RiderReviewRepository;

@Service("riderReviewService")
public class RiderReviewServiceImpl implements RiderReviewService {
	
	@Autowired
	RiderReviewRepository riderReviewRepository;
	
	public RiderReview saveReview(Integer stars, String description, Trip trip) {
		RiderReview riderReview = new RiderReview();
		riderReview.setStars(stars);
		riderReview.setDescription(description);
		riderReview.setTrip(trip);
		riderReview = riderReviewRepository.saveAndFlush(riderReview);
		return riderReview;
	}


}
