package com.stavroula.postexample.service;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.CreditCard;
import com.stavroula.postexample.entity.Rider;

@Service
public interface CreditCardService {
	
	public CreditCard getCreditCard(Long creditCard);
	public CreditCard saveCreditCard (Long cardNumber,String name,Rider rider);


}
