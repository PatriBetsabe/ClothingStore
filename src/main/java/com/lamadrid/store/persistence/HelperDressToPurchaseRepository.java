package com.lamadrid.store.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.domain.DressToPurchase;
import com.lamadrid.store.domain.DressToPurchaseId;

public interface HelperDressToPurchaseRepository extends CrudRepository<DressToPurchase, DressToPurchaseId> {
	
	DressToPurchase findByPurchase(int purchaseId);
	
	List<DressToPurchase> findAllByPurchase (Purchase purchase);
	
	DressToPurchase findByDress(int dressId);
	
	List<DressToPurchase> findAllByDress (Dress dress);
	
	@Transactional
	void removeByPurchase(Purchase purchase);
	
	@Transactional
	void removeByDress(Dress dress);
	

	
}
