package com.lamadrid.store.persistence;

import org.springframework.data.repository.CrudRepository;

import com.lamadrid.store.domain.Purchase;

public interface HelperPurchaseRepository extends CrudRepository<Purchase, Integer> {

}
