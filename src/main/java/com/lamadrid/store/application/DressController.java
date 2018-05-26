package com.lamadrid.store.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lamadrid.store.application.dto.DressDTO;
import com.lamadrid.store.domain.Dress;
import com.lamadrid.store.domain.DressToPurchase;
import com.lamadrid.store.persistence.DressRepository;
import com.lamadrid.store.persistence.DressToPurchaseRepository;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Controller
public class DressController {

	@Autowired
	private DressRepository dressRepository;
	@Autowired
	private DressToPurchaseRepository purchaseDressRepository;

	public DressDTO createDress(DressDTO dressDTO) throws InvalidParamException, NotFoundException{

		Dress dress = new Dress(dressDTO.getModel(), dressDTO.getColor(), dressDTO.getSize(),
				dressDTO.getPrice_sold(),dressDTO.getPrice_cost(), dressDTO.getStock(), dressDTO.getImageUrl());
		
		dressRepository.save(dress);
		
		return new DressDTO(dress);
		
	}
	
	public DressDTO updateDress(int dressId, DressDTO dressToUpdate) throws NotFoundException, InvalidParamException {
		
		Dress dress = dressRepository.getDressById(dressId);
		
		if(dressToUpdate.getPrice_sold()>0)
			dress.setPrice_sold(dressToUpdate.getPrice_sold());
		
		if(dressToUpdate.getPrice_cost()>0)
			dress.setPrice_cost(dressToUpdate.getPrice_cost());
		
		if(!dressToUpdate.getModel().equals(""))
			dress.setModel(dressToUpdate.getModel());
		
		if(!dressToUpdate.getColor().equals(""))
			dress.setColor(dressToUpdate.getColor());
		
		if(dressToUpdate.getSize()>0)
			dress.setSize(dressToUpdate.getSize());
		
		if(dressToUpdate.getStock()>0)
			dress.setSize(dressToUpdate.getSize());
		
		if(!dressToUpdate.getImageUrl().equals(""))
			dress.setImageUrl(dressToUpdate.getImageUrl());
	
		dressRepository.save(dress);
		
		return new DressDTO(dress);

		
	}
	
	Dress getDress(int dressId) throws NotFoundException {
		Dress dress = dressRepository.getDressById(dressId);
		
		return dress;
	}
	
	List<Dress> getAllDresses() {
		List<Dress> dresses = dressRepository.getAllDresses();
		
		return dresses;
	}
	
	public DressDTO getDressDTO(int dressId) throws NotFoundException {
		
		Dress dress = dressRepository.getDressById(dressId);
		
		return new DressDTO(dress);
	}
	
	public DressDTO getCurrentStock(int dressId) throws InvalidParamException, NotFoundException {

		Dress dress = dressRepository.getDressById(dressId);
		
		List<DressToPurchase> dressesToBuy = purchaseDressRepository.getAllDressesToPurchasesByDress(dress);

		double stockForUpdating = 0;
		for (DressToPurchase s : dressesToBuy)
			stockForUpdating += s.updateStock();
		
		dress.setStock(dress.getStock() - (stockForUpdating));

		dressRepository.save(dress);
		return new DressDTO(dress);

	}

	public List<DressDTO> listDresses() throws NotFoundException{
		List<Dress> dressList = dressRepository.getAllDresses();
		List<DressDTO> dressDTOList = new ArrayList<>();
		
		if(dressList.isEmpty())
			throw new NotFoundException();
		
		for(Dress d : dressList) {
			dressDTOList.add(new DressDTO(d));
		}
		return dressDTOList;
	}
	
	public void removeDress(int dressId) {

		dressRepository.removeDress(dressId);
	}
	
	public void removeDresses() {
		
		dressRepository.removeDresses();
	}
	
	
}
