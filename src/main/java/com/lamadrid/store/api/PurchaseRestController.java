package com.lamadrid.store.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lamadrid.store.application.PurchaseController;
import com.lamadrid.store.application.dto.PurchaseDTO;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@RestController
@CrossOrigin
//@RequestMapping("users/{userId}/purchases")
public class PurchaseRestController {
	
	@Autowired
	private PurchaseController controller;
	
	private String toJson(Object object) {
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(object);
	}
	
	@PostMapping(value = "/users/{userId}/purchase" , produces = "application/json;charset=UTF-8" )
	public String createNewPurchase(@PathVariable int userId ,@RequestBody String jPurchase) throws InvalidParamException, NotFoundException {
		
		PurchaseDTO purchaseDTO = new Gson().fromJson(jPurchase, PurchaseDTO.class);
		
		PurchaseDTO purchase = controller.createPurchase(userId, purchaseDTO);
		
		return toJson(purchase);
	}
	
}
