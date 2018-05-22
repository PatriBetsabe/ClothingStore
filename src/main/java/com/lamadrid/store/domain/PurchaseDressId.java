package com.lamadrid.store.domain;

import java.io.Serializable;

import javax.persistence.Column;


public class PurchaseDressId implements Serializable {
	
	@Column(name= "purchase_id")
	private int purchaseId;
	
	@Column( name = "dress_id")
	private int dressId;
	
	private PurchaseDressId() {}
	
	public PurchaseDressId(int purchaseId, int dressId) {
		this.purchaseId = purchaseId;
		this.dressId = dressId;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public int getDressId() {
		return dressId;
	}
	
	
	

}
