package com.stavroula.postexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.Photo;
import com.stavroula.postexample.entity.PhotoType;
import com.stavroula.postexample.service.CarService;
import com.stavroula.postexample.service.PhotoService;

@RestController
@RequestMapping("/cars")
public class CarController {

	@Autowired 
	CarService carService;
	
	@Autowired
	PhotoService photoService;
	
	@RequestMapping(value = "" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Car>> getCars(){
		List<Car> cars = carService.getCars();
		return ResponseEntity.accepted().body(cars);
	}
	
	@RequestMapping(value = "/{driverId}/car/{carId}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Car> getCar(@PathVariable Long driverId, @PathVariable Long carId){
		Car car = carService.getCar(carId);
		return ResponseEntity.accepted().body(car);	
	}
	
	 @RequestMapping(value="/{carId}/photo/{type}", method = RequestMethod.POST)
	  public ResponseEntity<Photo> setPhoto(@RequestBody String jsonStr,@PathVariable Long carId,@PathVariable(value = "type") PhotoType photoType){
		  Photo jsonObject = new Gson().fromJson(jsonStr, Photo.class);
		  
		  Car car = carService.getCar(carId);
		  String url = jsonObject.getUrl();
		  PhotoType type = photoType;
		  Photo photo = photoService.savePhoto(url,type,car);
		  return  ResponseEntity.accepted().body(photo);
	  }
	
	@RequestMapping(value = "{carId}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCar(@PathVariable long carId) {
       // logger.info(carId);
 
        Car car = carService.getCar(carId);
        if (car == null) {
           // logger.error("Unable to delete. Car with id {} not found.", carId);
           // return new ResponseEntity(new CustomErrorType("Unable to delete. Car with id " + carId + " not found."),
           //         HttpStatus.NOT_FOUND);
        }
        carService.deleteCar(carId);
        return new ResponseEntity<Car>(HttpStatus.NO_CONTENT);
    }
		
	}
	