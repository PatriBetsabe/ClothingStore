package com.lamadrid.store.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lamadrid.store.application.dto.PurchaseDressDTO;
import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.domain.PurchaseDress;
import com.lamadrid.store.persistence.PurchaseDressRepository;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Controller
public class PurchaseDressController {

	@Autowired
	private PurchaseDressRepository purchaseDressRepository;
	@Autowired
	private DressController dressController;
	@Autowired
	private PurchaseController purchaseController;

	public PurchaseDressDTO addDressToPurchase(int purchaseId, int dressId, PurchaseDressDTO newPurchaseDress)
			throws NotFoundException, InvalidParamException {

		Purchase purchase = purchaseController.getPurchase(purchaseId);

		Dress dress = dressController.getDress(dressId);

		PurchaseDress purchaseDress = new PurchaseDress(purchase, dress,newPurchaseDress.getQuantity());

		purchaseDressRepository.save(purchaseDress);

		return new PurchaseDressDTO(purchaseDress);

	}

}
