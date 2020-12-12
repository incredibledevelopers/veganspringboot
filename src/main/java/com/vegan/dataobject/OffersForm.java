package com.vegan.dataobject;

import java.util.List;

import com.vegan.model.Offers;

public class OffersForm {
	private String message;
	private boolean changeStatus;
	private String addOfferText;
	private String offerStatus;
	private List<Offers> offers;
	private List<String> offerIds;
	
	public OffersForm(boolean changeStatus, String message) {
		super();
		this.changeStatus = changeStatus;
		this.message = message;
	}
	
	public OffersForm() {}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isChangeStatus() {
		return changeStatus;
	}
	public void setChangeStatus(boolean changeStatus) {
		this.changeStatus = changeStatus;
	}
	public String getAddOfferText() {
		return addOfferText;
	}

	public void setAddOfferText(String addOfferText) {
		this.addOfferText = addOfferText;
	}

	public String getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}
	public List<Offers> getOffers() {
		return offers;
	}
	public void setOffers(List<Offers> offers) {
		this.offers = offers;
	}
	public List<String> getOfferIds() {
		return offerIds;
	}
	public void setOfferIds(List<String> offerIds) {
		this.offerIds = offerIds;
	}
	
}
