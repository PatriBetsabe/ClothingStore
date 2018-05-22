package com.lamadrid.store.domain;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "purchase_dress")
public class PurchaseDress {
	
	@EmbeddedId
	private PurchaseDressId id;
 
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("purchase_id")
	private Purchase purchase;
 
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("dress_id")
	private Dress dress;
 

	private double cost;
 
	private PurchaseDress() {}
 
	public PurchaseDress(Purchase purchase, Dress dress, double cost) {
    	this.purchase = purchase;
    	this.dress = dress;
    	this.id = new PurchaseDressId(purchase.getId(), dress.getId());
    	this.cost = cost;
	}
 
	public double getCost() {
		return cost;
	}
	
	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Dress getDress() {
		return dress;
	}

	public void setDress(Dress dress) {
		this.dress = dress;
	}
	
	

	public PurchaseDressId getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
    	if (this == o) return true;
 
    	if (o == null || getClass() != o.getClass())
        	return false;
 
    	PurchaseDress that = (PurchaseDress) o;
    	return Objects.equals(purchase, that.purchase) &&
           	Objects.equals(dress, that.dress);
	}
 


	@Override
	public int hashCode() {
    	return Objects.hash(purchase, dress);
	}


}
