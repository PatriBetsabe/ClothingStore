package com.lamadrid.store.application.dto;

import com.google.gson.annotations.Expose;
import com.lamadrid.store.domain.DressToPurchase;
import com.lamadrid.store.domain.DressToPurchaseId;
import com.lamadrid.store.utilities.NotFoundException;

public class DressToPurchaseDTO {

	private DressToPurchaseId id;
	@Expose
	private double price_unit,quantity, subtotal;
	@Expose
	private boolean isPaidAndUpdated;

	public DressToPurchaseDTO(DressToPurchase purchaseDress) throws NotFoundException {
		if (purchaseDress == null)
			throw new NotFoundException();

		this.id = purchaseDress.getId();
		this.price_unit = purchaseDress.getPrice_unit();
		this.quantity = purchaseDress.getQuantity();
		this.subtotal = purchaseDress.getSubtotal();
		this.isPaidAndUpdated = purchaseDress.isPaidAndUpdated();
	}

	public DressToPurchaseId getId() {
		return id;
	}

	public double getPrice_unit() {
		if (this.price_unit <= 0)
			return 0;
		return price_unit;
	}

	public double getQuantity() {
		if (this.quantity < 0)
			return 0;
		return quantity;
	}

	public double getSubtotal() {
		if (this.subtotal <= 0)
			return 0;
		return subtotal;
	}

	public boolean isPaidAndUpdated() {
		return isPaidAndUpdated;
	}

}
