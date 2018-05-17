package com.lamadrid.store.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Repository
public class DressRepository {
	
	@Autowired
	private HelperDressRepository repository;
	
	public void save(Dress dress) throws InvalidParamException, NotFoundException {
		if(dress==null)
			throw new NotFoundException();
		
		try {
			repository.save(dress);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidParamException("Repeated user");
		}
	}
	

	public Dress getDressById(int dressId) throws NotFoundException {
		try {
			return repository.findById(dressId).get();
		}catch(Exception e) {
			throw new NotFoundException();
		}
	}
	
	/*public void removeDresses(Purchase purchase) {
		repository.removeByPurchase(purchase);
	}*/
	
	public void removeDress(int dressId) {
		repository.deleteById(dressId);
	}

}
