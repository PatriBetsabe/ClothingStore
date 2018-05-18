package com.lamadrid.store.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.Purchase;

interface HelperDressRepository extends CrudRepository<Dress, Integer> {

	Dress findByPurchase(int purchaseId);
	
	List<Dress> findAllByPurchase (Purchase purchase);
	
	@Transactional
	void removeByPurchase(Purchase purchase);
	
}
