package com.vegan.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vegan.dataobject.OffersForm;
import com.vegan.model.Offers;
import com.vegan.repository.OffersRepository;

@Service
public class OffersService {

	@Autowired
	OffersRepository offersRepository;
	
	Logger logger = LoggerFactory.getLogger(OffersService.class);
	
	public List<Offers> findAll(){
		return offersRepository.findAll();
	}
	
	public List<Offers> findAvailableOffers(){
		return offersRepository.findAvailableOffers();
	}
	
	@Transactional
	public int toggleOfferStatus(OffersForm offersForm, String modifiedBy) {
		List<Long> offerIds = offersForm.getOfferIds().stream().filter(offer -> offer != null).map(offer -> Long.valueOf(offer)).collect(Collectors.toList());
		logger.info("Offer Status ="+offersForm.getOfferStatus()+" Offer Ids = "+offerIds);
		int count = offersRepository.toggleOfferStatus(offersForm.getOfferStatus(), modifiedBy, offerIds);
		return count;
	}
	
	public boolean addOffer(OffersForm offersForm, String addedBy) {
		Offers offer = new Offers();
		offer.setName(offersForm.getAddOfferText());
		offer.setAddedBy(addedBy);
		offer.setOfferAvailability("Y");
		Offers offerSaved = offersRepository.save(offer);
		return offerSaved != null;
	}
}
