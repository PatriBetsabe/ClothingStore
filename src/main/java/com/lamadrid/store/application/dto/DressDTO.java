package com.lamadrid.store.application.dto;

import com.google.gson.annotations.Expose;
import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.utilities.NotFoundException;

public class DressDTO {
	
	@Expose
	private int id;
	@Expose
	private String model, color, imageUrl;
	@Expose
	private int size;
	@Expose
	private double price_sold, stock;
	private double price_cost;
	
	public DressDTO(Dress dress) throws NotFoundException {
		if(dress==null) throw new NotFoundException();
		id=dress.getId();
		model=dress.getModel();
		color=dress.getColor();
		imageUrl=dress.getImageUrl();
		size=dress.getSize();
		price_sold=dress.getPrice_sold();
		price_cost=dress.getPrice_cost();
		stock = dress.getStock();
	}

	public int getId() {
		return id;
	}

	public String getModel() {
		if(this.model==null) return "";
		return model;
	}

	public String getColor() {
		if(this.color==null) return "";
		return color;
	}

	public String getImageUrl() {
		if(this.imageUrl==null) return "";
		return imageUrl;
	}

	public int getSize() {
		if(this.size<=0) return 0;
		return size;
	}
	
	public double getStock() {
		if(this.stock<=0) return 0;
		return stock;
	}

	public double getPrice_sold() {
		if(this.price_sold<=0) return 0;
		return price_sold;
	}
	
	public double getPrice_cost() {
		if(this.price_cost<=0) return 0;
		return price_cost;
	}
	

}
