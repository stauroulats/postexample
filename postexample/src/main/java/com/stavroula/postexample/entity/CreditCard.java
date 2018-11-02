package com.stavroula.postexample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CreditCard {

	@Id
	@GeneratedValue
	private Long id;
	private Long cardNumber;
	private String name;
	
	@ManyToOne
	private Rider rider;
	
	public CreditCard() {
		super();
	}
	
	public CreditCard(Long cardNumber, String name, Rider rider) {
		super();
		this.cardNumber = cardNumber;
		this.name = name;
		this.rider = rider;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rider getRider() {
		return rider;
	}

	public void setRider(Rider rider) {
		this.rider = rider;
	}
	
	
}
