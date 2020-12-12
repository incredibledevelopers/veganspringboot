package com.vegan.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name="offers_master")
public class Offers {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="offer_id")
    private Long id;
    
	@Column(name="offer_text")
    private String name;
	
	@Column(name="offer_availability")
    private String offerAvailability;
	
	@Column(name="added_by")
	private String addedBy;
	
	@CreationTimestamp
	@Column(name="add_date")
	private Timestamp addDate;
	
	@Column(name="modified_by")
	private String modifiedBy;
	
	@Column(name="modified_date")
	private String modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOfferAvailability() {
		return offerAvailability;
	}

	public void setOfferAvailability(String offerAvailability) {
		this.offerAvailability = offerAvailability;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Timestamp getAddDate() {
		return addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
