package com.lamadrid.store.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.domain.PurchaseDress;
import com.lamadrid.store.domain.PurchaseDressId;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Repository
public class PurchaseDressRepository {

	@Autowired
	private HelperPurchaseDressRepository repository;

	public void save(PurchaseDress purchaseDress) throws InvalidParamException {
		if (purchaseDress == null)
			throw new InvalidParamException();
		try {
			repository.save(purchaseDress);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidParamException();
		}
	}

	public PurchaseDress getPurchaseDressById(PurchaseDressId id) throws NotFoundException {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	public PurchaseDress getPurchaseDressByPurchase(int purchaseId) throws NotFoundException {
		try {
			return repository.findByPurchase(purchaseId);
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	public PurchaseDress getPurchaseDressByDress(int dressId) throws NotFoundException {
		try {
			return repository.findByDress(dressId);
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	public List<PurchaseDress> getAllPurchasesDress() {
		List<PurchaseDress> result = new ArrayList<>();

		for (PurchaseDress pd : repository.findAll()) {
			result.add(pd);

		}

		return result;
	}

	public List<PurchaseDress> getAllPurchasesDressesByPurchase(Purchase purchase) {
		List<PurchaseDress> result = new ArrayList<>();

		for (PurchaseDress p : repository.findAllByPurchase(purchase)) {
			result.add(p);
		}

		return result;
	}

	public List<PurchaseDress> getAllPurchasesDressesByDress(Dress dress) {
		List<PurchaseDress> result = new ArrayList<>();

		for (PurchaseDress d : repository.findAllByDress(dress)) {
			result.add(d);
		}

		return result;
	}

	public void removePurchaseDress(PurchaseDressId id) {
		repository.deleteById(id);
	}

	public void removePurchaseDressByPurchase(Purchase purchase) {
		repository.removeByPurchase(purchase);
	}

	public void removePurchaseDressByDress(Dress dress) {
		repository.removeByDress(dress);
	}

	public void removePurchasesDresses() {
		repository.deleteAll(getAllPurchasesDress());
	}

}
