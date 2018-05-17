package com.lamadrid.store.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.domain.User;

public interface HelperPurchaseRepository extends CrudRepository<Purchase, Integer> {

}
