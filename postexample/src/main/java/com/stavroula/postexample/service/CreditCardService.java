package com.stavroula.postexample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.CreditCard;
import com.stavroula.postexample.entity.Rider;

@Service
public interface CreditCardService {
	
	public CreditCard getCreditCard(Long creditCardId);
	public List<CreditCard> getAllCreditCards(Rider rider);
	public CreditCard saveCreditCard (Long cardNumber,String name,Rider rider);


}
