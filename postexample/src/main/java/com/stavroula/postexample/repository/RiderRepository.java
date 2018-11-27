package com.stavroula.postexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.Rider;

@Repository("riderRepository")
public interface RiderRepository extends JpaRepository<Rider, Long> {

}
