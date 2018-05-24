package com.lamadrid.store.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lamadrid.store.application.dto.DressToPurchaseDTO;
import com.lamadrid.store.application.dto.PurchaseDTO;
import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.DressToPurchase;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.domain.User;
import com.lamadrid.store.persistence.DressToPurchaseRepository;
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
	private DressToPurchaseRepository purchaseDressRepository;
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
		
		
		//Spurchase.setTotal(newPurchaseDress.getSubtotal());

		purchaseDressRepository.save(purchaseDress);

		return new DressToPurchaseDTO(purchaseDress);

	}
	
	public void removeDressToPurchase(int purchaseId, int dressId) throws NotFoundException {
		
		purchaseDressRepository.removeDressToPurchaseByIds(purchaseId, dressId);

	}
	
	/*if(user.getId() != purchase.getUser().getId())
		throw new InvalidParamException();*/
	
	public PurchaseDTO pay(int purchaseId) throws InvalidParamException, NotFoundException {
		
		Purchase purchase = purchaseRepository.getPurchaseById(purchaseId);
		
		purchase.setPayment(1);
		
		purchaseRepository.save(purchase);
		
		return new PurchaseDTO(purchase);
		
	}
	
	public PurchaseDTO getPurchase(int userId, int purchaseId) throws NotFoundException, InvalidParamException {
		User user = userController.getUser(userId);

		Purchase purchase = purchaseRepository.getPurchaseById(purchaseId);
		
		if(user.getId() != purchase.getUser().getId())
			throw new InvalidParamException();
		
		return new PurchaseDTO(purchase);
		
		
	}
	
	public List<PurchaseDTO> getAllPurchases(int userId) throws NotFoundException, InvalidParamException {
		
		User user = userController.getUser(userId);
		
		if(user.getId() != userId)
			throw new InvalidParamException();
		
		List<PurchaseDTO> purchaseDTO = new ArrayList<>();
		
		List<Purchase> purchases = purchaseRepository.getAllPurchases(user);
		for(Purchase p : purchases)
			purchaseDTO.add(new PurchaseDTO(p));
		
		return purchaseDTO;
	}
	
	public void removePurchases(int userId) throws NotFoundException {
		User user = userController.getUser(userId);
		purchaseRepository.removePurchases(user);
	} 
	
	public void removePurchaseOfUser(int userId, int purchaseId) throws NotFoundException, InvalidParamException {
		
		User user = userController.getUser(userId);
		Purchase purchase = purchaseRepository.getPurchaseById(purchaseId);
		
		if(user.getId() != purchase.getUser().getId())
			throw new InvalidParamException();
		
		purchaseRepository.removePurchase(purchaseId);
		
	}


}
