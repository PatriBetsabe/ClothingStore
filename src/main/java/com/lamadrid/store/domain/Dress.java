package com.lamadrid.store.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.lamadrid.store.utilities.InvalidParamException;

@Entity(name = "dress")
public class Dress {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;
	private String model;
	private String color;
	@Column(name = "image_url")
	private String imageUrl;

	public static final int XS = 1;
	public static final int S = 2;
	public static final int M = 3;
	public static final int L = 4;
	public static final int XL = 5;
	public static final int XXL = 6;

	private int size;
	private int stock;
	private double price_sold;
	private double price_cost;

	@OneToMany(mappedBy = "dress")

	private List<DressToPurchase> purchases = new ArrayList<>();

	public Dress() {

	}

	public Dress(String model, String color, int size, double price_sold, double price_cost, int stock, String imageUrl)
			throws InvalidParamException {
		if (model.equals("") || color.equals("") || price_cost <= 0 || price_sold <= 0 || stock <= 0)
			throw new InvalidParamException();

		if (!imageUrl.contains(".jpg"))
			throw new InvalidParamException();

		if (size != XS && size != S && size != M && size != L && size != XL && size != XXL)
			throw new InvalidParamException();

		this.size = size;
		this.model = model;
		this.color = color;
		this.price_sold = price_sold;
		this.price_cost = price_cost;
		this.stock = stock;
		this.imageUrl = imageUrl;
	}

	public Integer getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) throws InvalidParamException {
		if (model.equals(""))
			throw new InvalidParamException();
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) throws InvalidParamException {
		if (color.equals(""))
			throw new InvalidParamException();
		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) throws InvalidParamException {
		if (size <= 0)
			throw new InvalidParamException();
		this.size = size;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		if (imageUrl.contains(".jpg"))
			this.imageUrl = imageUrl;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) throws InvalidParamException {
		if (stock <= 0)
			throw new InvalidParamException();
		this.stock = stock;
	}

	public double getPrice_sold() {
		return price_sold;
	}

	public void setPrice_sold(double price_sold) throws InvalidParamException {
		if (price_sold <= 0)
			throw new InvalidParamException();
		this.price_sold = price_sold;
	}

	public double getPrice_cost() {
		return price_cost;
	}

	public void setPrice_cost(double price_cost) throws InvalidParamException {
		if (price_cost <= 0)
			throw new InvalidParamException();
		this.price_cost = price_cost;
	}

	public void setPurchases(List<DressToPurchase> purchases) {
		this.purchases = purchases;
	}

	public List<DressToPurchase> getPurchases() {
		return purchases;
	}

}
