package com.vegan.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vegan.dataobject.OffersForm;
import com.vegan.model.Offers;
import com.vegan.service.OffersService;

@CrossOrigin(origins = "*", maxAge = 36000)
@Controller
public class OffersController {

	Logger logger = LoggerFactory.getLogger(OffersController.class);
	
	@Value("${offers.add.success.message}")
	private String offersAddSuccessMsg;
	
	@Value("${offers.add.error.message}")
	private String offersAddErrorMsg;
	
	@Value("${offers.status.update.success.message}")
	private String offersStatusUpdSuccessMsg;
	
	@Value("${offers.status.update.error.message}")
	private String offersStatusUpdErrorMsg;
	
	@Autowired
	OffersService offersService;
	
	@ResponseBody
	@GetMapping(value="/api/offers", headers="Accept=application/json", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Offers> findAvailableOffers() {
		List<Offers> offers = offersService.findAvailableOffers();
		logger.info("Offers = "+offers);
		return offers;
	}
	
	@GetMapping("/offers")
	public String getOffers(Model model){
		OffersForm offersForm = new OffersForm();
		setOffersModel(model,offersForm);
		return "offers";
	}
	
	@PostMapping("/toggleOfferStatus")
	public String toggleOfferStatus(@ModelAttribute OffersForm offersForm,
			Model model,Principal principal) {
		System.out.println(offersForm.getOfferStatus());
		System.out.println(offersForm.getOfferIds());
		int count = offersService.toggleOfferStatus(offersForm,principal.getName());
		
		if(count > 0) {
			OffersForm updStatusSuccess = new OffersForm(true, offersStatusUpdSuccessMsg);
			setOffersModel(model,updStatusSuccess);
		}else {
			OffersForm updStatusError = new OffersForm(false,offersStatusUpdErrorMsg);
			setOffersModel(model,updStatusError);
		}
		return "offers";
	}
	
	@PostMapping("/addOffer")
	public String addOffer(@ModelAttribute OffersForm offersForm,
			Model model,Principal principal) {
		System.out.println("Offer Text = "+offersForm.getAddOfferText());
		boolean status = offersService.addOffer(offersForm,principal.getName());
		if(status) {
			OffersForm updStatusSuccess = new OffersForm(true, offersAddSuccessMsg);
			setOffersModel(model,updStatusSuccess);
		}else {
			OffersForm updStatusError = new OffersForm(false,offersAddErrorMsg);
			setOffersModel(model,updStatusError);
		}
		return "offers";
	}
	
	private void setOffersModel(Model model,OffersForm offersForm) {
		List<Offers> offers = offersService.findAll();
		offersForm.setOffers(offers);
		model.addAttribute("offersForm", offersForm);
	}
}
