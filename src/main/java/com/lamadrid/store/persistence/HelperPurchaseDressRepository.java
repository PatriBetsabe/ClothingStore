package com.lamadrid.store.persistence;

import org.springframework.data.repository.CrudRepository;

import com.lamadrid.store.domain.PurchaseDress;
import com.lamadrid.store.domain.PurchaseDressId;

public interface HelperPurchaseDressRepository extends CrudRepository<PurchaseDress, PurchaseDressId> {

	
}
