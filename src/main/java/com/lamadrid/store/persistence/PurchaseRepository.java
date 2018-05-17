package com.lamadrid.store.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Repository
public class PurchaseRepository {
	
	@Autowired
	private HelperPurchaseRepository repository;
	
	public void save(Purchase purchase) throws InvalidParamException {
		if(purchase==null)
			throw new InvalidParamException();
		
		try {
			repository.save(purchase);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidParamException("Repeated user");
		}
	}
	
	public Purchase getPurchaseById(int purchaseId) throws NotFoundException {
		try {
			return repository.findById(purchaseId).get();
		}catch(Exception e) {
			throw new NotFoundException();
		}
	}
	
	/*public void removePurchases(User user) {
		repository.removeByUser(user);
	}*/
	
	public void removePurchase(int purchaseId) {
		repository.deleteById(purchaseId);
	}

}
