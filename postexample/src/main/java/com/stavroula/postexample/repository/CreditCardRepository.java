package com.stavroula.postexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stavroula.postexample.entity.CreditCard;
import com.stavroula.postexample.entity.Rider;


@Repository("creditCardRepository")
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
	
	@Query("select cd from CreditCard cd where cd.rider =:rider")
	 public List<CreditCard> findCreditCardsByUserId(@Param("rider") Rider rider);

}
