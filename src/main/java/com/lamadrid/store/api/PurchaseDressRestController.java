package com.lamadrid.store.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lamadrid.store.application.PurchaseDressController;
import com.lamadrid.store.application.dto.PurchaseDressDTO;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@RestController
@CrossOrigin
public class PurchaseDressRestController {

	@Autowired
	private PurchaseDressController controller;

	private String toJson(Object object) {

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(object);
	}

	@PostMapping(value = "/purchase/{purchaseId}/dress/{dressId}", produces = "application/json;charset=UTF-8")
	public String createPurchaseDress(@PathVariable int purchaseId, @PathVariable int dressId, @RequestBody String json)
			throws NotFoundException, InvalidParamException {
		PurchaseDressDTO purchaseDressDTO = new Gson().fromJson(json, PurchaseDressDTO.class);

		PurchaseDressDTO purchaseDress = controller.addDressToPurchase(purchaseId, dressId, purchaseDressDTO);

		return toJson(purchaseDress);

	}

}
