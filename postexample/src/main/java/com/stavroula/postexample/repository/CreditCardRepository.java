package com.stavroula.postexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.CreditCard;

@Repository("creditCardRepository")
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
