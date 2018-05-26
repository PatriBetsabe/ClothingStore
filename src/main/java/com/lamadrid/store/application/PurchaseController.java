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

	public PurchaseDTO createPurchase(int userId) throws NotFoundException, InvalidParamException {

		User user = userController.getUser(userId);
		Purchase purchase = new Purchase(user);
		purchaseRepository.save(purchase);
		return new PurchaseDTO(purchase);
	}

	public DressToPurchaseDTO addToCart(int purchaseId, int dressId, DressToPurchaseDTO addDressToShoppingList)
			throws NotFoundException, InvalidParamException {

		Purchase purchase = purchaseRepository.getPurchaseById(purchaseId);
		Dress dress = dressController.getDress(dressId);

		if (purchase.PaymentIsMade())
			throw new InvalidParamException("Purchase order is paid, please ask for a new purchase order");

		if (dress.getStock() == 0)
			throw new NotFoundException("No stock");

		DressToPurchase purchaseDress = new DressToPurchase(purchase, dress, addDressToShoppingList.getQuantity());

		purchaseDressRepository.save(purchaseDress);

		return new DressToPurchaseDTO(purchaseDress);

	}

	public PurchaseDTO calculateTotalToPay(int purchaseId) throws InvalidParamException, NotFoundException {

		Purchase purchase = purchaseRepository.getPurchaseById(purchaseId);

		List<DressToPurchase> dresses = purchaseDressRepository.getAllDressesToPurchasesByPurchase(purchase);

		double totalPrice = 0;
		for (DressToPurchase prices : dresses)
			totalPrice += prices.getSubtotal();

		purchase.setTotal(totalPrice);
		purchaseRepository.save(purchase);
		return new PurchaseDTO(purchase);

	}

	public PurchaseDTO toPay(int userId, int purchaseId) throws InvalidParamException, NotFoundException {

		User user = userController.getUser(userId);
		Purchase purchase = purchaseRepository.getPurchaseById(purchaseId);

		if (user.getId() != purchase.getUser().getId())
			throw new InvalidParamException("The purchase does not belong to the user indicated by parameter");

		if (purchase.PaymentIsMade())
			throw new InvalidParamException("The purchase is already paid");

		purchase.setPaymentIsMade(true);

		purchaseRepository.save(purchase);

		return new PurchaseDTO(purchase);
	}

	public PurchaseDTO getPurchase(int userId, int purchaseId) throws NotFoundException, InvalidParamException {
		User user = userController.getUser(userId);
		Purchase purchase = purchaseRepository.getPurchaseById(purchaseId);

		if (user.getId() != purchase.getUser().getId())
			throw new InvalidParamException("The purchase does not belong to the user indicated by parameter");

		return new PurchaseDTO(purchase);
	}

	public List<PurchaseDTO> getAllPurchases(int userId) throws NotFoundException, InvalidParamException {
		User user = userController.getUser(userId);

		if (user.getId() != userId)
			throw new InvalidParamException();

		List<PurchaseDTO> purchaseDTO = new ArrayList<>();

		List<Purchase> purchases = purchaseRepository.getAllPurchases(user);
		for (Purchase p : purchases)
			purchaseDTO.add(new PurchaseDTO(p));

		return purchaseDTO;
	}

	public void deletePurchaseLine(int purchaseId, int dressId) throws NotFoundException, InvalidParamException {
		Purchase purchase = purchaseRepository.getPurchaseById(purchaseId);

		if (purchase.PaymentIsMade())
			throw new InvalidParamException("You can not retract a purchase that is already paid!!");

		purchaseDressRepository.removeDressToPurchaseByIds(purchaseId, dressId);
	}

	public void cancelPurchaseOfUser(int userId, int purchaseId) throws NotFoundException, InvalidParamException {
		User user = userController.getUser(userId);
		Purchase purchase = purchaseRepository.getPurchaseById(purchaseId);

		if (purchase.PaymentIsMade())
			throw new InvalidParamException("You can not retract a purchase that is already paid!!");

		if (user.getId() != purchase.getUser().getId())
			throw new InvalidParamException();

		purchaseRepository.removePurchase(purchaseId);
	}
	
	public void removePurchases(int userId) throws NotFoundException {
		User user = userController.getUser(userId);
		purchaseRepository.removePurchases(user);
	}

}
