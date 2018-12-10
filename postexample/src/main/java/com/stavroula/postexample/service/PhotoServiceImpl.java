package com.stavroula.postexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Photo;
import com.stavroula.postexample.entity.PhotoType;
import com.stavroula.postexample.entity.User;
import com.stavroula.postexample.repository.CarRepository;
import com.stavroula.postexample.repository.DriverRepository;
import com.stavroula.postexample.repository.PhotoRepository;
import com.stavroula.postexample.repository.UserRepository;


@Service("photoService")
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PhotoRepository photoRepository;
	
	@Autowired
	DriverRepository driverRepository;
	
	@Autowired
	CarRepository carRepository;
	
	public Photo savePhoto(String url,PhotoType type,User user) {
		Photo photo = new Photo();
		photo.setUrl(url);
		photo.setType(type);
		photoRepository.save(photo);
		user.setPhoto(photo);
		userRepository.saveAndFlush(user);
		return photo;
	}
	
	public Photo savePhoto(String url,PhotoType type,Car car) {
		Photo photo = new Photo();
		photo.setUrl(url);
		photo.setType(type);
		photoRepository.save(photo);
		car.setPhoto(photo);
		carRepository.saveAndFlush(car);
		return photo;
	}

	
}
