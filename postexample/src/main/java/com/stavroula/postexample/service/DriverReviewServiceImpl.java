package com.stavroula.postexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.DriverReview;
import com.stavroula.postexample.entity.Trip;
import com.stavroula.postexample.repository.DriverReviewRepository;

@Service("driverReviewService")
public class DriverReviewServiceImpl implements DriverReviewService {
	
	@Autowired
	DriverReviewRepository driverReviewRepository;
	
	public DriverReview saveReview(Integer stars, String description, Trip trip) {
		DriverReview driverReview = new DriverReview();
		driverReview.setStars(stars);
		driverReview.setDescription(description);
		driverReview.setTrip(trip);
		driverReview = driverReviewRepository.saveAndFlush(driverReview);
		return driverReview;
	}

}
