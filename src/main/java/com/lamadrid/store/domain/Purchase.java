package com.lamadrid.store.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.lamadrid.store.utilities.InvalidParamException;

@Entity(name = "purchase")
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "purchase_date")
	private Calendar purchaseDate;
	private double payment;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;

	/*@OneToMany(targetEntity = Dress.class)
	@JoinColumn(name = "dress_id")
	private List<Dress> dresses = new ArrayList<>();*/

	public Purchase() {

	}

	public Purchase(User user, double payment) throws InvalidParamException {
		if(payment <= 0)
			throw new InvalidParamException();
		
		if(user==null) throw new InvalidParamException();
		
		this.user = user;
		this.purchaseDate = Calendar.getInstance();
		this.payment = payment;
	}
	
	public Purchase(double payment) throws InvalidParamException {
		if(payment <= 0)
			throw new InvalidParamException();
		
		if(user==null) throw new InvalidParamException();
		
		this.purchaseDate = Calendar.getInstance();
		this.payment = payment;
	}


	public Date getPurchaseDate() {
		return purchaseDate.getTime();
	}

	public Integer getId() {
		return id;
	}
	
	public double getPayment() {
		return payment;
	}

	/*public List<Dress> getDresses() {
		return new ArrayList<>(dresses);
	}

	public Dress addDress(Dress dress) throws NotFoundException {
		if (dress == null)
			throw new NotFoundException();
		dresses.add(dress);
		
		return dress;
	}*/

}
