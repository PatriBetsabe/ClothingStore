package com.lamadrid.store.application.dto;

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
	private List<DressDTO> dresses = new ArrayList<>();
	
	public PurchaseDTO(Purchase purchase) throws NotFoundException {
		if(purchase==null) throw new NotFoundException();
		
		id=purchase.getId();
		purchaseDate=purchase.getPurchaseDate();
		dresses=getDressDTO();
		
	}

	private List<DressDTO> getDressDTO() {
		List<DressDTO> dresses = new ArrayList<>();
		dresses.add(Dress.)
		return dresses;
	}

}
