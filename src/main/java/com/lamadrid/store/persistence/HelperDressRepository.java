package com.lamadrid.store.persistence;

import org.springframework.data.repository.CrudRepository;

import com.lamadrid.store.domain.Dress;

interface HelperDressRepository extends CrudRepository<Dress, Integer> {

	/*Dress findByPurchase(int purchaseId);
	
	List<Dress> findAllByPurchase (Purchase purchase);
	*/
	/*@Transactional
	void removeByPurchase(Purchase purchase);*/
	
}
