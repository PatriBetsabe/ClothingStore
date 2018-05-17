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
		if(model == null) return "";
		return model;
	}

	public String getColor() {
		if(color == null) return "";
		return color;
	}

	public String getImageUrl() {
		if(imageUrl == null) return "";
		return imageUrl;
	}

	public int getSize() {
		if(size == 0) return 0;
		return size;
	}

	public double getPrice() {
		if(price == 0) return 0;
		return price;
	}
	

}
