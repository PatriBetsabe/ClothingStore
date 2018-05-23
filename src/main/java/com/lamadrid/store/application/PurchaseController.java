package com.lamadrid.store.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lamadrid.store.application.dto.DressToPurchaseDTO;
import com.lamadrid.store.application.dto.PurchaseDTO;
import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.DressToPurchase;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.domain.User;
import com.lamadrid.store.persistence.DressToRepositoryRepository;
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
	private DressToRepositoryRepository purchaseDressRepository;
	@Autowired
	private DressController dressController;
	

	public PurchaseDTO createPurchase(int userId, PurchaseDTO purchaseDTO ) throws NotFoundException, InvalidParamException {
		
		User user = userController.getUser(userId);
		
		Purchase purchase = new Purchase(user, purchaseDTO.getPayment(), purchaseDTO.getTotal());
		
		purchaseRepository.save(purchase);
		
		return new PurchaseDTO(purchase);
	}
	
	public DressToPurchaseDTO addDressToPurchase(int purchaseId, int dressId, DressToPurchaseDTO newPurchaseDress)
			throws NotFoundException, InvalidParamException {

		Purchase purchase = purchaseRepository.getPurchaseById(purchaseId);

		Dress dress = dressController.getDress(dressId);

		DressToPurchase purchaseDress = new DressToPurchase(purchase, dress,newPurchaseDress.getQuantity());

		purchaseDressRepository.save(purchaseDress);

		return new DressToPurchaseDTO(purchaseDress);

	}


}
