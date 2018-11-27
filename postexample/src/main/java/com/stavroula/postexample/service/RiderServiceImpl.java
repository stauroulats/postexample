package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.repository.RiderRepository;

@Service("riderService")
public class RiderServiceImpl implements RiderService{
	
	@Autowired
	RiderRepository riderRepository;
	
	public List<Rider> getAllRiders(){
		return riderRepository.findAll();
	}
	
	public Rider getRider(Long riderId) {
		Optional<Rider> optinalEntity =  riderRepository.findById(riderId);
		Rider rider = optinalEntity.isPresent() ? optinalEntity.get():null;
		return rider;
	}

}
