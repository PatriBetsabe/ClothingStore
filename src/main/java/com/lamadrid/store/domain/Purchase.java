package com.lamadrid.store.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Entity(name = "purchase") 
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "purchase_date")
	private Calendar purchaseDate;
	
	@Column (name= "payment_is_made",nullable = false)
	@Type (type = "org.hibernate.type.NumericBooleanType")
	private boolean paymentIsMade;
	
	private double total;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany( mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PurchaseLine> dresses = new ArrayList<>();

	public Purchase() {

	}

	public Purchase(User user) throws InvalidParamException {
				
		if(user==null) throw new InvalidParamException();
		
		if(total <0) throw new InvalidParamException();
		
		this.user = user;
		this.purchaseDate = Calendar.getInstance();
		this.paymentIsMade = false;
		this.total = getTotal();
	}


	public Date getPurchaseDate() {
		return purchaseDate.getTime();
	}

	public Integer getId() {
		return id;
	}
	
	public boolean PaymentIsMade() {
		return paymentIsMade;
	}
	
	
	public void setPaymentIsMade(boolean paymentIsMade) {
		this.paymentIsMade = paymentIsMade;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) throws InvalidParamException {
		if (total <= 0)
			throw new InvalidParamException();
		this.total = total;
	}

	public List<PurchaseLine> getPurchaseDresses() {
		return new ArrayList<>(dresses);
	}

	
	public User getUser() {
		return user;
	}

	public void addDress(Dress dress, double quantity) throws NotFoundException, InvalidParamException {
		if (dress == null)
			throw new NotFoundException();
		PurchaseLine purchaseDress = new PurchaseLine(this, dress, quantity);
		dresses.add(purchaseDress);
		dress.getPurchases().add(purchaseDress);
		

	}
	
	public void removeDress(Dress dress) {
    	for (Iterator<PurchaseLine> iterator = dresses.iterator();
         	iterator.hasNext(); ) {
        	PurchaseLine purchaseDress = iterator.next();
 
        	if (purchaseDress.getPurchase().equals(this) &&
                	purchaseDress.getDress().equals(dress)) {
            	iterator.remove();
            	purchaseDress.getDress().getPurchases().remove(purchaseDress);
            	purchaseDress.setPurchase(null);
            	purchaseDress.setDress(null);
        	}
    	}
	}
 
	
}
