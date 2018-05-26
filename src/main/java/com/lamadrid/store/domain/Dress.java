package com.lamadrid.store.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.lamadrid.store.application.dto.DressDTO;
import com.lamadrid.store.utilities.InvalidParamException;

@Entity(name = "dress")
public class Dress {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	
	private String model,color;
	
	@Column(name = "image_url")
	private String imageUrl;

	public static final int XS = 1;
	public static final int S = 2;
	public static final int M = 3;
	public static final int L = 4;
	public static final int XL = 5;
	public static final int XXL = 6;

	private int size;
	private double stock,price_sold,price_cost;

	@OneToMany(mappedBy = "dress")
	private List<DressToPurchase> purchases = new ArrayList<>();

	public Dress() {

	}

	public Dress(DressDTO dressDTO) throws InvalidParamException {
		if(dressDTO==null) throw new InvalidParamException();
		
		checkNotEmpty(dressDTO.getModel());
		checkNotEmpty(dressDTO.getColor());
		checkValidImageUrl(dressDTO.getImageUrl());
		checkValidSize(dressDTO.getSize());
		checkCantidad(dressDTO.getPrice_cost());
		checkCantidad(dressDTO.getPrice_sold());
		checkCantidad(dressDTO.getStock());

		this.model = dressDTO.getModel();
		this.color = dressDTO.getColor();
		this.imageUrl = dressDTO.getImageUrl();
		this.size = dressDTO.getSize();
		this.price_sold = dressDTO.getPrice_sold();
		this.price_cost = dressDTO.getPrice_cost();
		this.stock = dressDTO.getStock();
		
	}
	
	private void checkValidImageUrl(String imageUrl) throws InvalidParamException {
		if (imageUrl == null || !imageUrl.contains(".jpg"))
			throw new InvalidParamException();
	}
	
	private void checkNotEmpty(String text) throws InvalidParamException {
		if (text == null || text.equals(""))
			throw new InvalidParamException();
	}
	
	private void checkValidSize(int size) throws InvalidParamException {
		if (size != XS && size != S && size != M && size != L && size != XL && size != XXL)
			throw new InvalidParamException();
	}
	
	private void checkCantidad(double cantidad) throws InvalidParamException {
		if (cantidad <= 0)
			throw new InvalidParamException();
	}

	public Integer getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) throws InvalidParamException {
		checkNotEmpty(model);
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) throws InvalidParamException {
		checkNotEmpty(color);
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) throws InvalidParamException {
		checkValidSize(size);
		this.size = size;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) throws InvalidParamException {
		checkValidImageUrl(imageUrl);
			this.imageUrl = imageUrl;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) throws InvalidParamException {
		checkCantidad(stock);
		this.stock = stock;
	}

	public double getPrice_sold() {
		return price_sold;
	}

	public void setPrice_sold(double price_sold) throws InvalidParamException {
		checkCantidad(price_sold);
		this.price_sold = price_sold;
	}

	public double getPrice_cost() {
		return price_cost;
	}

	public void setPrice_cost(double price_cost) throws InvalidParamException {
		checkCantidad(price_cost);
		this.price_cost = price_cost;
	}

	public List<DressToPurchase> getPurchases() {
		return purchases;
	}

}
