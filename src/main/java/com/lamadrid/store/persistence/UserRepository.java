package com.lamadrid.store.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lamadrid.store.domain.User;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Repository
public class UserRepository {
	
	@Autowired
	private HelperUserRepository repository;
	
	public void save(User user) throws NotFoundException, InvalidParamException {
		if(user==null) throw new NotFoundException();
		
		try {
			repository.save(user);
		}catch(Exception e) {
			e.printStackTrace();
			throw new InvalidParamException("Repeated user");
		}
	}
	
	public User getUserByEmail(String email) throws InvalidParamException {
		User user = repository.findByEmail(email);
		if(user==null) throw new InvalidParamException();
		return user;
	}
	
	public List<User> getAllUsers(){
		List<User> result = new ArrayList<>();
		
		for(User u : repository.findAll()) {
			result.add(u);
		}
		return result;
		
	}
	
	public User getUserById(int userId) throws NotFoundException {
		try {
			return repository.findById(userId).get();
		}catch(Exception e) {
			throw new NotFoundException();
		}
	}
}
