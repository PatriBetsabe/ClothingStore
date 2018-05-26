package com.lamadrid.store.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lamadrid.store.application.dto.UserDTO;
import com.lamadrid.store.domain.User;
import com.lamadrid.store.persistence.UserRepository;
import com.lamadrid.store.utilities.InvalidParamException;
import com.lamadrid.store.utilities.NotFoundException;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	public UserDTO register(UserDTO userDTO) throws NotFoundException, InvalidParamException {
		User user = new User(userDTO);
		userRepository.save(user);
		return new UserDTO(user);
	}

	public UserDTO login(UserDTO userToLogin) throws InvalidParamException, NotFoundException {
		User user = userRepository.getUserByEmail(userToLogin.getEmail());
		user.checkPasswordIsCorrect(userToLogin.getPassword());
		return new UserDTO(user);
	}

	public List<UserDTO> listUsers() throws NotFoundException {
		List<User> userList = userRepository.getAllUsers();
		List<UserDTO> userDTOList = new ArrayList<>();

		if (userList.isEmpty())
			throw new NotFoundException();

		for (User u : userList) {
			userDTOList.add(new UserDTO(u));
		}

		return userDTOList;
	}

	User getUser(int userId) throws NotFoundException {
		User user = userRepository.getUserById(userId);

		return user;
	}

	public UserDTO getUserDTO(int userId) throws NotFoundException {
		User user = userRepository.getUserById(userId);
		return new UserDTO(user);
	}

	public UserDTO updateUser(int userId, UserDTO userToUpdate) throws NotFoundException, InvalidParamException {

		User user = userRepository.getUserById(userId);

		if (!userToUpdate.getEmail().equals(""))
			user.setEmail(userToUpdate.getEmail());

		if (!userToUpdate.getName().equals(""))
			user.setName(userToUpdate.getName());

		userRepository.save(user);
		return new UserDTO(user);
	}

	public void removeUser(int userId) {
		userRepository.removeUser(userId);
	}

	public void removeUsers() {
		userRepository.removeUsers();
	}

}
