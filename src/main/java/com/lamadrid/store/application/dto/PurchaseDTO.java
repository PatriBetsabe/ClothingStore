package com.lamadrid.store.application.dto;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.annotations.Expose;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.utilities.NotFoundException;

public class PurchaseDTO {
	
	@Expose
	private int id;
	@Expose
	private Date purchaseDate;
	@Expose
	private boolean paymentIsMade;
	@Expose
	private double total;
	/*@Expose
	private List<DressDTO> dressesDTO = new ArrayList<>();*/
	
	public PurchaseDTO(Purchase purchase) throws NotFoundException {
		if(purchase==null) throw new NotFoundException();
		this.id=purchase.getId();
		this.purchaseDate=purchase.getPurchaseDate();
		this.paymentIsMade=purchase.PaymentIsMade();
		this.total=purchase.getTotal();
		//initListDressesDTO(purchase);
	}

	/*private void initListDressesDTO(Purchase purchase) throws NotFoundException{
		
		List<Dress> dresses = purchase.getDresses();
		
		for(Dress d : dresses)
			dressesDTO.add(new DressDTO(d));
	}*/

	public int getId() {
		return id;
	}

	
	
	public boolean PaymentIsMade() {
		return paymentIsMade;
	}

	public double getTotal() {
		if(this.total<0) return 0;
		return total;
	}

	/*public List<DressDTO> getDressesDTO() {
		return dressesDTO;
	}*/

	public String getPurchaseDateToString() {
		SimpleDateFormat format1 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		return format1.format(purchaseDate.getTime());

	}

}
