package com.lamadrid.store.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lamadrid.store.application.dto.DressDTO;
import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.persistence.DressRepository;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Controller
public class DressController {

	@Autowired
	private DressRepository dressRepository;

	public DressDTO createDress(DressDTO dressDTO) throws InvalidParamException, NotFoundException {

		Dress dress = new Dress(dressDTO.getModel(), dressDTO.getColor(), dressDTO.getSize(),
				dressDTO.getPrice(), dressDTO.getImageUrl());
		
		dressRepository.save(dress);
		
		return new DressDTO(dress);
		
	}
	
	public DressDTO updateDress(int dressId, DressDTO dressToUpdate) throws NotFoundException, InvalidParamException {
		
		Dress dress = dressRepository.getDressById(dressId);
		
		if(dressToUpdate.getPrice()<=0)
			dress.setPrice(dressToUpdate.getPrice());
		
		if(!dressToUpdate.getModel().equals(""))
			dress.setModel(dressToUpdate.getModel());
		
		if(!dressToUpdate.getColor().equals(""))
			dress.setColor(dressToUpdate.getColor());
		
		if(dressToUpdate.getSize()<=0)
			dress.setSize(dressToUpdate.getSize());
	
		dressRepository.save(dress);
		
		return new DressDTO(dress);

		
	}
	
	/*private DressDTO updatePriceOfDress(int dressId, DressDTO priceToUpdate) throws InvalidParamException, NotFoundException {
		
		Dress dress = dressRepository.getDressById(dressId);
		
		if(priceToUpdate.getPrice()<=0)
			dress.setPrice(priceToUpdate.getPrice());
		
		dressRepository.save(dress);
		
		return new DressDTO(dress);
	}
	
	private DressDTO updateModelOfDress(int dressId, DressDTO modelToUpdate) throws NotFoundException, InvalidParamException {
		
		Dress dress = dressRepository.getDressById(dressId);
		
		if(!modelToUpdate.getModel().equals(""))
			dress.setModel(modelToUpdate.getModel());
		
		dressRepository.save(dress);
		
		return new DressDTO(dress);
	}
	
	private DressDTO updateColorOfDress(int dressId, DressDTO colorToUpdate) throws NotFoundException, InvalidParamException {
		
		Dress dress = dressRepository.getDressById(dressId);
		
		if(!colorToUpdate.getColor().equals(""))
			dress.setColor(colorToUpdate.getColor());
		
		dressRepository.save(dress);
		
		return new DressDTO(dress);
				
	}
	
	private DressDTO updateSizeOfDress(int dressId, DressDTO sizeToUpdate) throws NotFoundException, InvalidParamException {
		
		Dress dress = dressRepository.getDressById(dressId);
		
		if(sizeToUpdate.getSize()<=0)
			dress.setSize(sizeToUpdate.getSize());
		
		dressRepository.save(dress);
		
		return new DressDTO(dress);
	}*/
	
	

}
