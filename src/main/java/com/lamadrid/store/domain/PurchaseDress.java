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
 

	private double price_unit;
	private double quantity;
	private double subtotal;
 
	private PurchaseDress() {}
 
	public PurchaseDress(Purchase purchase, Dress dress, double quantity) {
    	this.purchase = purchase;
    	this.dress = dress;
    	this.id = new PurchaseDressId(purchase.getId(), dress.getId());
    	this.price_unit = dress.getPrice_sold();
    	this.quantity = quantity;
    	this.subtotal = dress.getPrice_sold()*quantity;
	}
 
	public double getPrice_unit() {
		return price_unit;
	}
	
	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public void setPrice_unit(double price_unit) {
		this.price_unit = price_unit;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
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

	
	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
