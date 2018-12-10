package com.stavroula.postexample.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class CreditCard {

	@Id
	@GeneratedValue
	private Long id;
	private Long cardNumber;
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="rider_Id",nullable = false)
	@JsonBackReference
	private Rider rider;
	
	public CreditCard() {
		super();
	}
	
	public CreditCard(Long cardNumber, String name,Rider rider) {
		super();
		this.cardNumber = cardNumber;
		this.name = name;
		this.rider = rider;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
}
}
