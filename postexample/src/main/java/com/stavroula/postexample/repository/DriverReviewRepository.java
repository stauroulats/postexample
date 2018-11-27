package com.stavroula.postexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.DriverReview;

@Repository("driverReviewRepository")
public interface DriverReviewRepository extends JpaRepository<DriverReview, Long> {

}
