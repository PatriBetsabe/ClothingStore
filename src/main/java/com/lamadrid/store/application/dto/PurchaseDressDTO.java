package com.lamadrid.store.application.dto;

import com.google.gson.annotations.Expose;
import com.lamadrid.store.domain.PurchaseDress;
import com.lamadrid.store.domain.PurchaseDressId;

public class PurchaseDressDTO {

	@Expose
	private PurchaseDressId id;
	@Expose
	private double cost;

	public PurchaseDressDTO(PurchaseDress purchaseDress) {
		this.id = purchaseDress.getId();
		this.cost = purchaseDress.getCost();

	}

	public PurchaseDressId getId() {
		return id;
	}

	public double getCost() {
		return cost;
	}

}
