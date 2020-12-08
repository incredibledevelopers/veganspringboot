package com.vegan.dataobject;

import java.util.Arrays;
import java.util.List;

public class Order {
	private String customerName;
	private String address;
	private boolean status;
	private String orderId;
	private String message;
    private String mobileNumber;
    private String fromDate;
    private String toDate;
    private double deliveryCharges;
	private String cancellationReason;
    private ProductOrders[] productOrders;
    private List<String> orderIds;
    
    public Order(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
    
    public Order(){}
    
    public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public double getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(double deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	public ProductOrders[] getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(ProductOrders[] productOrders) {
		this.productOrders = productOrders;
	}

	public List<String> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}

	@Override
	public String toString() {
		return "Order [customerName=" + customerName + ", address=" + address + ", mobileNumber=" + mobileNumber
				+ ", productOrders=" + Arrays.toString(productOrders) + "]";
	}

	
}
