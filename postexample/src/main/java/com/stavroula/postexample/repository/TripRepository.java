package com.stavroula.postexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.Trip;

@Repository("tripRepository")
public interface TripRepository extends JpaRepository<Trip, Long> {

}