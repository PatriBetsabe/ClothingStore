package com.lamadrid.store.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.domain.PurchaseDress;
import com.lamadrid.store.domain.PurchaseDressId;

public interface HelperPurchaseDressRepository extends CrudRepository<PurchaseDress, PurchaseDressId> {
	
	PurchaseDress findByPurchase(int purchaseId);
	
	List<PurchaseDress> findAllByPurchase (Purchase purchase);
	
	PurchaseDress findByDress(int dressId);
	
	List<PurchaseDress> findAllByDress (Dress dress);
	
	@Transactional
	void removeByPurchase(Purchase purchase);
	
	@Transactional
	void removeByDress(Dress dress);
	

	
}
