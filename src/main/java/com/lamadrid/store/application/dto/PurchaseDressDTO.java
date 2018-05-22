package com.lamadrid.store.application.dto;

import com.google.gson.annotations.Expose;
import com.lamadrid.store.domain.PurchaseDress;
import com.lamadrid.store.domain.PurchaseDressId;

public class PurchaseDressDTO {

	//@Expose
	//private PurchaseDressId id;
	@Expose
	private double price_unit;
	@Expose
	private double quantity;
	@Expose
	private double subtotal;

	public PurchaseDressDTO(PurchaseDress purchaseDress) {
		//this.id = purchaseDress.getId();
		this.price_unit = purchaseDress.getPrice_unit();
		this.quantity = purchaseDress.getQuantity();
		this.subtotal = purchaseDress.getSubtotal();

	}

	public PurchaseDressId getId() {
		return null;//return id;
	}

	public double getPrice_unit() {
		return price_unit;
	}
	
	public double getQuantity() {
		return quantity;
	}
	
	public double getSubtotal() {
		return subtotal;
	}
	

}
