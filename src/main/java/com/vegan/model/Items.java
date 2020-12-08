package com.vegan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="items_master")
public class Items {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
    private Long id;
    
	@Column(name="item_name")
    private String name;
	
	@Column(name="item_url")
    private String pictureUrl;
	
	@Column(name="item_price")
	private double price;
    
	@Column(name="item_rate")
	private String itemRate;
	
	@Column(name="item_category")
	private String itemCategory;
	
	@Column(name="item_availability")
	private String itemAvailability;
	
	@Column(name="added_by")
	private String addedBy;
	
	@Column(name="modified_by")
	private String modifiedBy;

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

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getItemRate() {
		return itemRate;
	}

	public void setItemRate(String itemRate) {
		this.itemRate = itemRate;
	}
	
	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemAvailability() {
		return itemAvailability;
	}

	public void setItemAvailability(String itemAvailability) {
		this.itemAvailability = itemAvailability;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", price=" + price + ", itemRate=" + itemRate
				+ ", itemAvailability=" + itemAvailability + "]";
	}	
}
