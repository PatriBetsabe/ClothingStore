package com.lamadrid.store.application.dto;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.utilities.NotFoundException;

public class PurchaseDTO {
	
	@Expose
	private int id;
	@Expose
	private Date purchaseDate;
	@Expose
	private List<DressDTO> dresses = new ArrayList<>();
	
	@Expose
	private double payment;
	@Expose
	private List<DressDTO> dressesDTO = new ArrayList<>();
	
	public PurchaseDTO(Purchase purchase) throws NotFoundException {
		if(purchase==null) throw new NotFoundException();
		this.id=purchase.getId();
		this.purchaseDate=purchase.getPurchaseDate();
		this.payment=purchase.getPayment();
		initListDressesDTO(purchase);
	}

	private void initListDressesDTO(Purchase purchase) throws NotFoundException{
		
		List<Dress> dresses = purchase.getDresses();
		
		for(Dress d : dresses)
			dressesDTO.add(new DressDTO(d));
	}

	public int getId() {
		return id;
	}


	public double getPayment() {
		return payment;
	}


	public List<DressDTO> getDressesDTO() {
		return dressesDTO;
	}


	public String getPurchaseDateToString() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(purchaseDate.getTime());

	}

}
