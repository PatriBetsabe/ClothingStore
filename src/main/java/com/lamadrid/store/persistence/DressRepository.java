package com.lamadrid.store.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Repository
public class DressRepository {
	
	@Autowired
	private HelperDressRepository repository;
	
	public void save(Dress dress) throws InvalidParamException{
		if(dress == null)
			throw new InvalidParamException();
		try {
			repository.save(dress);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidParamException("Repeated dress");
		}

	}
	

	public Dress getDressById(int dressId) throws NotFoundException {
		try {
			return repository.findById(dressId).get();
		}catch(Exception e) {
			throw new NotFoundException();
		}
	}
	
	public Dress getDressByPurchase(int purchaseId) throws NotFoundException {
		try {
			return repository.findByPurchase(purchaseId);
		}catch(Exception e) {
			throw new NotFoundException();
		}
	}
	
	public List<Dress> getAllDresses() {
		List<Dress> result = new ArrayList<>();
		
		for(Dress d : repository.findAll()) {
			result.add(d);
		}
		
		return result;
	}
	
	public List<Dress> getAllDressesByPurchase(Purchase purchase) {
		List<Dress> result = new ArrayList<>();
		
		for(Dress d : repository.findAllByPurchase(purchase)) {
			result.add(d);
		}
		
		return result;
	}
	
	public void removeByPurchase(Purchase purchase) {
		repository.removeByPurchase(purchase);
		
	}
	
	public void removeDress(int dressId) {
		repository.deleteById(dressId);
		
	}
	
	public void removeDresses() {
		repository.deleteAll(getAllDresses());
	}

}
