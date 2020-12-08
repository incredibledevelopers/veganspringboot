package com.vegan.dataobject;

public class ProductOrders {
	private Product product;
    private String quantity;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ProductOrders [product=" + product + ", quantity=" + quantity + "]";
	}
}
