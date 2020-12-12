package com.vegan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vegan.model.Offers;

public interface OffersRepository extends CrudRepository<Offers, Long>{
	
	@Query("select offers from offers_master offers where offer_availability='Y' order by addDate desc")
	List<Offers> findAvailableOffers();
	
	List<Offers> findAll();
	
	@Modifying
	@Query("update offers_master offers set offers.offerAvailability= ?1, offerAvailability.modifiedBy = ?2 where offerAvailability.id IN (?3)")
	int toggleOfferStatus(String status,String modifiedBy, List<Long> ids);
	
}
