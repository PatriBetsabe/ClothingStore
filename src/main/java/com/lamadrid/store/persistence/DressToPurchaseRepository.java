package com.lamadrid.store.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.Purchase;
import com.lamadrid.store.domain.DressToPurchase;
import com.lamadrid.store.domain.DressToPurchaseId;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Repository
public class DressToPurchaseRepository {

	@Autowired
	private HelperDressToPurchaseRepository repository;

	public void save(DressToPurchase purchaseDress) throws InvalidParamException {
		if (purchaseDress == null)
			throw new InvalidParamException();
		try {
			repository.save(purchaseDress);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidParamException();
		}
	}

	public DressToPurchase getDressToPurchaseById(DressToPurchaseId id) throws NotFoundException {
		try {
			return repository.findById(id).get();
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	public DressToPurchase getDressToPurchaseByPurchase(Purchase purchase) throws NotFoundException {
		try {
			return repository.findByPurchase(purchase);
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	public DressToPurchase getDressToPurchaseByDress(Dress dress) throws NotFoundException {
		try {
			return repository.findByDress(dress);
		} catch (Exception e) {
			throw new NotFoundException();
		}
	}

	public List<DressToPurchase> getAllDressToPurchases() {
		List<DressToPurchase> result = new ArrayList<>();

		for (DressToPurchase pd : repository.findAll()) {
			result.add(pd);

		}

		return result;
	}

	public List<DressToPurchase> getAllDressesToPurchasesByPurchase(Purchase purchase) {
		List<DressToPurchase> result = new ArrayList<>();

		for (DressToPurchase p : repository.findAllByPurchase(purchase)) {
			result.add(p);
		}

		return result;
	}

	public List<DressToPurchase> getAllDressesToPurchasesByDress(Dress dress) {
		List<DressToPurchase> result = new ArrayList<>();

		for (DressToPurchase d : repository.findAllByDress(dress)) {
			result.add(d);
		}

		return result;
	}

/*	public void removeDressToPurchase(DressToPurchaseId id) {
		repository.deleteById(id);
	}*/

	public void removeDressToPurchaseByPurchase(Purchase purchase) {
		repository.removeByPurchase(purchase);
	}

	public void removeDressToPurchaseByIds(int purchaseId, int dressId) {
		repository.deleteById(new DressToPurchaseId(purchaseId, dressId));
	}

	public void removeDressesToPurchases() {
		repository.deleteAll(getAllDressToPurchases());
	}

}
