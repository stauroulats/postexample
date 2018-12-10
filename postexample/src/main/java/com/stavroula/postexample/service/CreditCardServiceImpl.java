package com.stavroula.postexample.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stavroula.postexample.entity.CreditCard;
import com.stavroula.postexample.entity.Rider;
import com.stavroula.postexample.repository.CreditCardRepository;
import com.stavroula.postexample.repository.RiderRepository;

@Service("creditCardService")
public class CreditCardServiceImpl implements CreditCardService {
	
	@Autowired
	CreditCardRepository creditCardRepository;
	
	@Autowired
	RiderRepository riderRepository;
	
	@Transactional
	public CreditCard getCreditCard(Long creditCardId) {
		Optional<CreditCard> optionalEntity =  creditCardRepository.findById(creditCardId);
		CreditCard creditCard = optionalEntity.isPresent() ? optionalEntity.get():null;
		return creditCard;
	}
	
	public List<CreditCard> getAllCreditCards(Rider rider) {
		return creditCardRepository.findCreditCardsByUserId(rider);
	}
	
	public CreditCard saveCreditCard(Long cardNumber,String name,Rider rider) {
		CreditCard creditCard = new CreditCard(cardNumber,name,rider);
		creditCard.setCardNumber(cardNumber);
		creditCard.setName(name);
		rider.addCreditCard(creditCard);
		riderRepository.saveAndFlush(rider);
		return creditCard;
	}

}
