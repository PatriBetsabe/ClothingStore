package com.lamadrid.store.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lamadrid.store.utilities.InvalidParamException;

@Entity(name = "dress")
public class Dress {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String model;
	private String color;
	private double price;
	@Column(name = "image_url")
	private String imageUrl;
	
	public static final int XS = 1;
	public static final int S = 2;
	public static final int M = 3;
	public static final int L = 4;
	public static final int XL = 5;
	public static final int XXL = 6;
	
	private int size;
	
	@ManyToOne(targetEntity = Purchase.class)
	@JoinColumn(name = "purchase_id")
	private Purchase purchase;

	public Dress() {

	}
	
	public Dress(String model, String color, int size, double price, String imageUrl) throws InvalidParamException{
		if (model.equals("") || color.equals("") || price <= 0) throw new InvalidParamException();
		
		if (!imageUrl.contains(".jpg")) throw new InvalidParamException();
		
		if(size != XS && size != S && size != M && size != L && size != XL && size != XXL) throw new InvalidParamException();
		
		this.size = size;
		this.model = model;
		this.color = color;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public Dress(Purchase purchase, String model, String color, int size, double price, String imageUrl) throws InvalidParamException{
		if (model.equals("") || color.equals("") || price <= 0) throw new InvalidParamException();
		
		if (!imageUrl.contains(".jpg")) throw new InvalidParamException();
		
		if(size != XS && size != S && size != M && size != L && size != XL && size != XXL) throw new InvalidParamException();
		
		this.purchase = purchase;
		this.size = size;
		this.model = model;
		this.color = color;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public Integer getId() {
		return id;
	}

	public String getModel() {
		return model;
	}
	
	public void setModel(String model) throws InvalidParamException {
		if(model.equals(""))
			throw new InvalidParamException();
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) throws InvalidParamException {
		if(color.equals(""))
			throw new InvalidParamException();
		this.color = color;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) throws InvalidParamException {
		if(size <= 0)
			throw new InvalidParamException();
		this.size = size;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) throws InvalidParamException {
		if(price <= 0)
			throw new InvalidParamException();
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		if (imageUrl.contains(".jpg"))
			this.imageUrl = imageUrl;
	}

}
