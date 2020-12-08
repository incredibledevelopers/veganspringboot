package com.vegan.dataobject;

import java.util.List;

public class ProductForm {
	private boolean changeStatus;
	private String message;
	private String itemStatus;
	private List<Product> products;
	
	public ProductForm(boolean changeStatus, String message) {
		super();
		this.changeStatus = changeStatus;
		this.message = message;
	}
	
	public ProductForm() {}
	
	public boolean getChangeStatus() {
		return changeStatus;
	}
	public void setChangeStatus(boolean changeStatus) {
		this.changeStatus = changeStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
