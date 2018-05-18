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
import com.lamadrid.store.application.UserController;
import com.lamadrid.store.application.dto.UserDTO;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@CrossOrigin
@RestController
public class UserRestController {
	
	@Autowired
	private UserController controller;
	
	private String toJson(Object object) {
		
		Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(object);
	}
	
	@PostMapping(value = "/users" , produces = "application/json;charset=UTF-8" )
	public String register(@RequestBody String jUser) throws InvalidParamException, NotFoundException {
		
		UserDTO newUser = new Gson().fromJson(jUser, UserDTO.class);
		
		UserDTO user = controller.register(newUser);
		
		return toJson(user);
	}
	
	@PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
	public String login(@RequestBody String jUser) throws NotFoundException, InvalidParamException {

		UserDTO userToLogin = new Gson().fromJson(jUser, UserDTO.class);

		UserDTO user = controller.login(userToLogin);

		return toJson(user);
	}
	
	@PutMapping(value = "/users/{userId}" , produces = "application/json;charset=UTF-8")
	public String updateUser(@PathVariable int userId, @RequestBody String jUser) throws NotFoundException, InvalidParamException {
		
		UserDTO userToUpdate = new Gson().fromJson(jUser, UserDTO.class);
		
		UserDTO user = controller.updateUser(userId, userToUpdate);
		
		return toJson(user);
	}
	
	@GetMapping(value = "/users" , produces = "application/json;charset=UTF-8")
	public String listUsers() throws NotFoundException {
		
		List<UserDTO> users = controller.listUsers();
		
		return toJson(users);
	}
	
	@GetMapping(value = "/users/{userId}" , produces = "application/json;charset=UTF-8")
	public String getDress(@PathVariable int userId) throws NotFoundException {
		
		UserDTO user = controller.getUserDTO(userId);
		
		return toJson(user);
	}
	
	@DeleteMapping(value = "/users" , produces = "application/json;charset=UTF-8")
	public void removeAllUsers() {
		
		controller.removeUsers();
	}
	
	@DeleteMapping(value = "/users/{userId}" , produces = "application/json;charset=UTF-8")
	public void removeDress(@PathVariable int userId) {
		
		controller.removeUser(userId);
		
	}
	

}
