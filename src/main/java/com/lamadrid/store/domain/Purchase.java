package com.lamadrid.store.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import javax.persistence.*;

import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Entity(name = "purchase") 
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "purchase_date")
	private Calendar purchaseDate;
	private int payment;
	private double total;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany( mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PurchaseDress> dresses = new ArrayList<>();

	public Purchase() {

	}

	public Purchase(User user, int payment, double total) throws InvalidParamException {
				
		if(user==null) throw new InvalidParamException();
		
		this.user = user;
		this.purchaseDate = Calendar.getInstance();
		this.payment = payment;
		this.total = total;
	}


	public Date getPurchaseDate() {
		return purchaseDate.getTime();
	}

	public Integer getId() {
		return id;
	}
	
	public int getPayment() {
		return payment;
	}

	public double getTotal() {
		return total;
	}

	public List<PurchaseDress> getPurchaseDresses() {
		return new ArrayList<>(dresses);
	}

	public void addDress(Dress dress) throws NotFoundException {
		if (dress == null)
			throw new NotFoundException();
		PurchaseDress purchaseDress = new PurchaseDress(this, dress, payment);
		dresses.add(purchaseDress);
		dress.getPurchases().add(purchaseDress);
		

	}
	
	public void removeDress(Dress dress) {
    	for (Iterator<PurchaseDress> iterator = dresses.iterator();
         	iterator.hasNext(); ) {
        	PurchaseDress purchaseDress = iterator.next();
 
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
