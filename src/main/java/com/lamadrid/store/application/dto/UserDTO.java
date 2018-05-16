package com.lamadrid.store.application.dto;

import com.google.gson.annotations.Expose;
import com.lamadrid.store.domain.User;
import com.lamadrid.store.utilities.NotFoundException;

public class UserDTO {
	
	@Expose 
	private int id;
	@Expose
	private String name,email;
	private String password;
		
	public UserDTO(User user) throws NotFoundException{
		if(user==null) throw new NotFoundException();
		name=user.getName();
		email=user.getEmail();
		password=user.getPassword();
		id=user.getId();
	}

	public String getName() {
		if(this.name==null) return "";
		return name;
	}

	public String getEmail() {
		if(this.email==null) return "";
		return email;
	}

	public String getPassword() {
		if(this.password==null) return "";
		return password;
	}

	public int getId() {
		return id;
	}
	
	

}
