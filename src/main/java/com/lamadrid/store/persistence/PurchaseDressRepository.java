package com.lamadrid.store.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lamadrid.store.domain.PurchaseDress;
import com.lamadrid.store.domain.PurchaseDressId;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Repository
public class PurchaseDressRepository {
	
	@Autowired
	private HelperPurchaseDressRepository repository;
	
	public void save(PurchaseDress purchaseDress) throws InvalidParamException {
		if(purchaseDress == null)
			throw new InvalidParamException();
		try {
			repository.save(purchaseDress);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidParamException();
		}
	}
	
	public PurchaseDress getPurchaseDressById(PurchaseDressId id) throws NotFoundException {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			throw new NotFoundException();
		} 
	}
	
	
	

}
