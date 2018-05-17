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
	private double price;
	
	public DressDTO(Dress dress) throws NotFoundException {
		if(dress==null) throw new NotFoundException();
		this.id=dress.getId();
		this.model=dress.getModel();
		this.color=dress.getColor();
		this.imageUrl=dress.getImageUrl();
		this.size=dress.getSize();
		this.price=dress.getPrice();
	}

	public int getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public String getColor() {
		return color;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public int getSize() {
		return size;
	}

	public double getPrice() {
		return price;
	}
	

}
