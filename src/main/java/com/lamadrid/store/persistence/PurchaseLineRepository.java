package com.lamadrid.store.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.domain.PurchaseLine;
import com.lamadrid.store.domain.PurchaseLineId;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Repository
public class PurchaseLineRepository {

	@Autowired
	private HelperPurchaseLineRepository repository;

	public void save(PurchaseLine purchaseDress) throws InvalidParamException {
		if (purchaseDress == null)
			throw new InvalidParamException();
		try {
			repository.save(purchaseDress);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidParamException();
		}
	}

	public PurchaseLine getPurchaseLineById(int purchaseId, int dressId) throws NotFoundException {
		try {
			return repository.findById(new PurchaseLineId(purchaseId, dressId)).get();
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	public List<PurchaseLine> getAllPurchasesLines() {
		List<PurchaseLine> result = new ArrayList<>();

		for (PurchaseLine pd : repository.findAll()) {
			result.add(pd);

		}

		return result;
	}

	public List<PurchaseLine> getAllPurchasesLinesByPurchase(Purchase purchase) {
		List<PurchaseLine> result = new ArrayList<>();

		for (PurchaseLine p : repository.findAllByPurchase(purchase)) {
			result.add(p);
		}

		return result;
	}

	public List<PurchaseLine> getAllPurchasesLinesByDress(Dress dress) {
		List<PurchaseLine> result = new ArrayList<>();

		for (PurchaseLine d : repository.findAllByDress(dress)) {
			result.add(d);
		}

		return result;
	}

	public void removeDressToPurchaseByIds(int purchaseId, int dressId) {
		repository.deleteById(new PurchaseLineId(purchaseId, dressId));
	}


}
