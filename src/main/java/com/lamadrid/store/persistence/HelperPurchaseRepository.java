package com.lamadrid.store.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.domain.User;

interface HelperPurchaseRepository extends CrudRepository<Purchase, Integer> {
	
	List<Purchase> findAllByUser(User user);
	
	@Transactional
	void removeByUser(User user);

}
