package com.lamadrid.store.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.Purchase;

public interface HelperDressRepository extends CrudRepository<Dress, Integer> {

	
	/*@Transactional
	void removeByPurchase(Purchase purchase);*/
}
