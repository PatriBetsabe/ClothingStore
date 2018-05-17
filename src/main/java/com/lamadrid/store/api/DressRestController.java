package com.lamadrid.store.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lamadrid.store.application.DressController;
import com.lamadrid.store.application.dto.DressDTO;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@CrossOrigin
@RestController
public class DressRestController {
	
	@Autowired
	private DressController controller;
	
	private String toJson(Object object){
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(object);
	}
	
	@PostMapping(value = "/dresses" , produces = "application/json;charset=UTF-8" )
	public String createDress(@RequestBody String jDress) throws InvalidParamException, NotFoundException {
		
		DressDTO newDress = new Gson().fromJson(jDress, DressDTO.class);
		
		DressDTO dress = controller.createDress(newDress);
		
		return toJson(dress);
	}
	
	@PutMapping(value = "/dresses/{dressId}" , produces = "application/json;charset=UTF-8")
	public String updateDress(@PathVariable int dressId, @RequestBody String jDress) throws NotFoundException, InvalidParamException {
		
		DressDTO dressToUpdate = new Gson().fromJson(jDress, DressDTO.class);
		
		DressDTO dress = controller.updateDress(dressId, dressToUpdate);
		
		return toJson(dress);
	}
	
	

}
