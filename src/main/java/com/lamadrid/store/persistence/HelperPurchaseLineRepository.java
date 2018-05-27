package com.lamadrid.store.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.domain.PurchaseLine;
import com.lamadrid.store.domain.PurchaseLineId;

public interface HelperPurchaseLineRepository extends CrudRepository<PurchaseLine, PurchaseLineId> {
	
	PurchaseLine findByPurchase(Purchase purchase);
	
	List<PurchaseLine> findAllByPurchase (Purchase purchase);
	
	PurchaseLine findByDress(Dress dress);
	
	List<PurchaseLine> findAllByDress (Dress dress);
	
	@Transactional
	void removeByPurchase(Purchase purchase);
	
	@Transactional
	void removeByDress(Dress dress);
	

	
}
