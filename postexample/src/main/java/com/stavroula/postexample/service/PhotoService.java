package com.stavroula.postexample.service;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Photo;
import com.stavroula.postexample.entity.PhotoType;
import com.stavroula.postexample.entity.User;

@Service
public interface PhotoService {

	public Photo savePhoto(String url,PhotoType type,User user);
	public Photo savePhoto(String url,PhotoType type,Car car);
}
