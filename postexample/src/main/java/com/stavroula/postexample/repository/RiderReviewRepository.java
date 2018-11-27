package com.stavroula.postexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.RiderReview;

@Repository("riderReviewRepository")
public interface RiderReviewRepository extends JpaRepository<RiderReview, Long> {

}
