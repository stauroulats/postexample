package com.stavroula.postexample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Rider;

@Service
public interface RiderService {
	
	public List<Rider> getAllRiders();
	
	public Rider getRider(Long riderId);
	

}
