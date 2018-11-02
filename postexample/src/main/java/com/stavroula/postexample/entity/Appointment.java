package com.stavroula.postexample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue
	private Long id;
	private String pickUpPoint;
	private String destination;
	
	@ManyToOne
	private Rider rider;
	
	@ManyToOne
	private Driver driver;
	
	
}
