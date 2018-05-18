package com.lamadrid.store.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lamadrid.store.application.dto.PurchaseDTO;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.domain.User;
import com.lamadrid.store.persistence.PurchaseRepository;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Controller
public class PurchaseController {

	@Autowired
	private UserController userController;
	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
	private DressController dressController;

	/*public PurchaseDTO addDress(int dressId, PurchaseDTO newPurchase) throws NotFoundException, InvalidParamException {
		
		Dress dress = dressRepository.getDressById(dressId);

		Purchase purchase = new Purchase( newPurchase.getPayment());
		purchase.addDress(dress);
		
		purchaseRepository.save(purchase);
		
		return new PurchaseDTO(purchase);

	}*/
	
	public PurchaseDTO addPurchaseToUser(int userId, PurchaseDTO purchaseToUser) throws NotFoundException, InvalidParamException {
		
		User user = userController.getUser(userId);
		
		Purchase purchase = new Purchase(user, purchaseToUser.getPayment());
		
		return new PurchaseDTO(purchase);
	}

}
