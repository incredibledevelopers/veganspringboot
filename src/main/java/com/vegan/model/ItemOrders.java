package com.vegan.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name="item_orders")
public class ItemOrders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="order_id")
	private String orderId;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="order_address")
	private String orderAddress;
	
	@Column(name="item_name")
	private String itemName;
	
	@Column(name="item_quantity")
	private double itemQuantity;
	
	@Column(name="item_total_price")
	private double itemTotalPrice;
	
	@Column(name="delivery_charges")
	private double deliveryCharges;
	
	@Column(name="order_total")
	private double orderTotal;
	
	@Column(name="order_status")
	private String orderStatus;
	
	@CreationTimestamp
	@Column(name="order_time")
	private Timestamp orderTime;
	
	@Column(name="cancellation_reason")
	private String cancellationReason;
	
	@Column(name="modified_by")
	private String modifiedBy;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getOrderAddress() {
		return orderAddress;
	}


	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public double getItemQuantity() {
		return itemQuantity;
	}


	public void setItemQuantity(double itemQuantity) {
		this.itemQuantity = itemQuantity;
	}


	public double getItemTotalPrice() {
		return itemTotalPrice;
	}


	public void setItemTotalPrice(double itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}


	public double getDeliveryCharges() {
		return deliveryCharges;
	}


	public void setDeliveryCharges(double deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}


	public double getOrderTotal() {
		return orderTotal;
	}


	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Timestamp getOrderTime() {
		return orderTime;
	}


	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}


	public String getCancellationReason() {
		return cancellationReason;
	}


	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	@Override
	public String toString() {
		return "ItemOrders [id=" + id + ", orderId=" + orderId + ", mobileNumber=" + mobileNumber + ", orderAddress="
				+ orderAddress + ", itemName=" + itemName + ", itemQuantity=" + itemQuantity + ", itemTotalPrice="
				+ itemTotalPrice + ", orderTotal=" + orderTotal + ", orderStatus=" + orderStatus + ", modifiedBy="
				+ modifiedBy + "]";
	}
	
}

