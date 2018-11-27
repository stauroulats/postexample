package com.stavroula.postexample.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.CreditCard;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.repository.CreditCardRepository;

@Service("creditCardService")
public class CreditCardServiceImpl implements CreditCardService {
	
	@Autowired
	CreditCardRepository creditCardRepository;
	
	public CreditCard getCreditCard(Long creditCardId) {
		Optional<CreditCard> optinalEntity =  creditCardRepository.findById(creditCardId);
		CreditCard creditCard = optinalEntity.get();
		return creditCard;
		
	}
	
	public CreditCard saveCreditCard(Long cardNumber,String name,Rider rider) {
		CreditCard creditCard = new CreditCard();
		creditCard.setCardNumber(cardNumber);
		creditCard.setName(name);
		creditCard.setRider(rider);
		creditCard = creditCardRepository.saveAndFlush(creditCard);
		return creditCard;
	}

}
