package com.lamadrid.store.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.lamadrid.store.utilities.Encryptor;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Entity(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String name;
	private String email;
	private String password;

	@OneToMany(targetEntity = Purchase.class)
	@JoinColumn(name = "purchase_id")
	private List<Purchase> purchases = new ArrayList<>();

	public User() {

	}

	public User(String name, String email, String password) throws InvalidParamException {
		checkValidName(name);
		checkValidPassword(password);
		if (!email.contains("@"))
			throw new InvalidParamException();
		this.name = name;
		this.email = email;
		this.password = Encryptor.encryptPassword(password);
	}
	
	private void checkValidName(String name) throws InvalidParamException {
		if (name == null || name.equals(""))
			throw new InvalidParamException();
	}

	private void checkValidPassword(String password) throws InvalidParamException {
		if (password == null || password.length() < 7)
			throw new InvalidParamException();
	}
	
	public void checkPasswordIsCorrect(String password) throws InvalidParamException {
		Encryptor.checkIfPasswordMatches(password, this.password);
	}

	public void checkEmailCorrect(String email) throws InvalidParamException {
		if (!this.email.equals(email))
			throw new InvalidParamException();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws InvalidParamException {
		checkValidName(name);
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws InvalidParamException {	
		if (email.equals("") || email.contains("@"))
			throw new InvalidParamException();
		
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws InvalidParamException {
		checkValidPassword(password);
		this.password = password;
	}

	public List<Purchase> getPurchases() {
		return new ArrayList<>(purchases);
	}

	public Purchase addPurchase(Dress dress) throws NotFoundException{
		Purchase purchase = new Purchase();
		purchase.addDress(dress);
		if (purchase == null)
			throw new NotFoundException();
		purchases.add(purchase);
		
		return purchase;
	}

	
}
