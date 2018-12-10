package com.stavroula.postexample.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.Car;
import com.stavroula.postexample.entity.CreditCard;
import com.stavroula.postexample.entity.Driver;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.entity.User;
import com.stavroula.postexample.repository.CarRepository;
import com.stavroula.postexample.repository.CreditCardRepository;
import com.stavroula.postexample.repository.DriverRepository;
import com.stavroula.postexample.repository.RiderRepository;
import com.stavroula.postexample.repository.UserRepository;


@Service("dbSeedService")
public class DbSeedService {

	@Autowired
	RiderRepository riderRepository;
	
	@Autowired
	DriverRepository driverRepository;
	
	@Autowired
	UserRepository userRepository;
	CarRepository carRepository;
	CreditCardRepository creditCardRepository;

	public  void seedDb() { 
	List<User> users = addUsers();
	User user1 = users.get(0);
	User user2 = users.get(1);
	User user3 = users.get(2);
	
	/*Driver driver1 = driverRepository.saveAndFlush(new Driver(null, null, null, null, null));
	user1.setDriver(driver1);
	user1 = userRepository.saveAndFlush(user1);
	
	Driver driver2 = driverRepository.saveAndFlush(new Driver(null, null, null, null, null));
	user2.setDriver(driver2);
	user2 = userRepository.saveAndFlush(user2);*/
	
	Driver driver1 = new Driver();
	driver1.setUser(user1);
	driver1 = driverRepository.saveAndFlush(driver1);

	
	Rider rider1 = new Rider(); //riderRepository.saveAndFlush(new Rider(null, null, null, null, null));
	rider1.setUser(user3);
	//user3.setRider(rider1);
	//user3 = userRepository.saveAndFlush(user3);
	rider1 = riderRepository.saveAndFlush(rider1);
	
	
	//CREATE A CREDIT CARD
	/*CreditCard creditCard =new CreditCard();
	creditCard.setCardNumber((long) 234);
	creditCard.setName("voula");
	rider1.addCreditCard(creditCard);
	rider1 = riderRepository.saveAndFlush(rider1);*/

	
	}
	
	public List<User> addUsers() {
		User driver1 = new User("john" , "1234" , "john@yahoo.com");
		User driver2 = new User("tom", "sf239dh" , "tom23@gmail.com");
		User rider1 = new User("maryp", "m25@" , "mary@hotmail.com");
		List<User> users = new ArrayList<User>();
		users.add(driver1);
		users.add(driver2);
		users.add(rider1);
		
		users = userRepository.saveAll(users);
		
		return users;
		
		
	}

}
