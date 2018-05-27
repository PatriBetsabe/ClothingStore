package com.lamadrid.store.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.lamadrid.store.application.dto.UserDTO;
import com.lamadrid.store.utilities.Encryptor;
import com.lamadrid.store.utilities.InvalidParamException;

@Entity(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String name,email,password;

	public User() {

	}

	public User(UserDTO userDTO) throws InvalidParamException {
		if(userDTO==null) throw new InvalidParamException();
		
		checkValidName(userDTO.getName());
		checkValidPassword(userDTO.getPassword());
		checkValidEmail(userDTO.getEmail());

		this.name = userDTO.getName();
		this.email = userDTO.getEmail();
		this.password = Encryptor.encryptPassword(userDTO.getPassword());
	}

	private void checkValidName(String name) throws InvalidParamException {
		if (name == null || name.equals(""))
			throw new InvalidParamException();
	}

	private void checkValidPassword(String password) throws InvalidParamException { 
		if (password == null || password.length() < 7)
			throw new InvalidParamException();
	}

	private void checkValidEmail(String email) throws InvalidParamException {
		if (email == null || email.equals("") ||!email.contains("@"))
			throw new InvalidParamException();
	}

	public void checkPasswordIsCorrect(String password) throws InvalidParamException {
		Encryptor.checkIfPasswordMatches(password, this.password);
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
		checkValidEmail(email);
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws InvalidParamException {
		checkValidPassword(password);
		this.password = password;
	}

}
