package com.lamadrid.store.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lamadrid.store.application.DressController;
import com.lamadrid.store.application.dto.DressDTO;
import com.lamadrid.store.domain.Dress;
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
	public String createNewDress(@RequestBody String jDress) throws InvalidParamException, NotFoundException {
		
		DressDTO newDress = new Gson().fromJson(jDress, DressDTO.class);
		
		DressDTO dress = controller.createDress(newDress);
		
		return toJson(dress);
	}
	
	@PutMapping(value = "/dress/{dressId}" , produces = "application/json;charset=UTF-8")
	public String updateDress(@PathVariable int dressId, @RequestBody String jDress) throws NotFoundException, InvalidParamException {
		
		DressDTO dressToUpdate = new Gson().fromJson(jDress, DressDTO.class);
		
		DressDTO dress = controller.updateDress(dressId, dressToUpdate);
		
		return toJson(dress);
	}
	
	@PutMapping(value = "/dresses/{dressId}", produces = "application/json;charset=UTF-8")
	public String updateStock(@PathVariable int dressId) throws InvalidParamException, NotFoundException {
		
		DressDTO dress = controller.getCurrentStock(dressId);
		
		return toJson(dress);
	}
	
	@GetMapping(value = "/dresses" , produces = "application/json;charset=UTF-8")
	public String listDresses() throws NotFoundException {
		
		List<DressDTO> dresses = controller.listDresses();
		
		return toJson(dresses);
	}
	
	@GetMapping(value = "/dresses/{dressId}" , produces = "application/json;charset=UTF-8")
	public String getDress(@PathVariable int dressId) throws NotFoundException {
		
		DressDTO dress = controller.getDressDTO(dressId);
		
		return toJson(dress);
	}
	
	@DeleteMapping(value = "/dresses" , produces = "application/json;charset=UTF-8")
	public void removeAllDresses() {
		
		controller.removeDresses();
	}
	
	@DeleteMapping(value = "/dresses/{dressId}" , produces = "application/json;charset=UTF-8")
	public void removeDress(@PathVariable int dressId) {
		
		controller.removeDress(dressId);
		
	}
	
	
	

}
