package com.lamadrid.store.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.lamadrid.store.utilities.NotFoundException;

@Entity(name = "purshase")
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private Calendar purchaseDate;

	@OneToMany(targetEntity = Dress.class)
	@JoinColumn(name = "dress_id")
	private List<Dress> dresses = new ArrayList<>();

	public Purchase() {

	}

	public Purchase(String description) {
		purchaseDate = Calendar.getInstance();
	}

	public Date getPurchaseDate() {
		return purchaseDate.getTime();
	}

	public Integer getId() {
		return id;
	}

	public List<Dress> getDesses() {
		return new ArrayList<>(dresses);
	}

	public void addDress(Dress dress) throws NotFoundException {
		if (dress == null)
			throw new NotFoundException();
		dresses.add(dress);
	}

}