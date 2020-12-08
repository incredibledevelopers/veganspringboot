package com.vegan.dataobject;

public class Product {
	private String price;
    private String name;
    private String itemCategory;
    private Long itemId;
    private String itemAvailability;
    private String itemRate;
    private String lastModifiedBy;
    
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public String getItemAvailability() {
		return itemAvailability;
	}
	public void setItemAvailability(String itemAvailability) {
		this.itemAvailability = itemAvailability;
	}
	public String getItemRate() {
		return itemRate;
	}
	public void setItemRate(String itemRate) {
		this.itemRate = itemRate;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	@Override
	public String toString() {
		return "Product [price=" + price + ", name=" + name + ", itemId=" + itemId + "]";
	}
	
}
