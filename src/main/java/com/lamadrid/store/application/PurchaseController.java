package com.lamadrid.store.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lamadrid.store.application.dto.PurchaseDTO;
import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.persistence.DressRepository;
import com.lamadrid.store.persistence.PurchaseRepository;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Controller
public class PurchaseController {

	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
	private DressRepository dressRepository;

	public PurchaseDTO addDress(int dressId, PurchaseDTO newPurchase) throws NotFoundException, InvalidParamException {
		
		Dress dress = dressRepository.getDressById(dressId);

		Purchase purchase = new Purchase( newPurchase.getPayment());
		purchase.addDress(dress);
		
		purchaseRepository.save(purchase);
		
		return new PurchaseDTO(purchase);

	}

}
