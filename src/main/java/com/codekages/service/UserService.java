package com.codekages.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codekages.dao.UserDAO;
import com.codekages.dto.AddUserDTO;
import com.codekages.exception.BadParameterException;
import com.codekages.model.User;

@Service
public class UserService {

private UserDAO userDAO;
	
	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User addUser(AddUserDTO addUserDTO) throws BadParameterException {
		if(addUserDTO.getUsername().trim().equals("")) {
			throw new BadParameterException("Username field is blank!");
		}
		if(addUserDTO.getPassword().trim().equals("")) {
			throw new BadParameterException("Password field is blank!");
		}
		if(addUserDTO.getFirstName().trim().equals("")) {
			throw new BadParameterException("First Name field is blank!");
		}
		if(addUserDTO.getLastName().trim().equals("")) {
			throw new BadParameterException("Last Name field is blank!");
		}
		
		User user = userDAO.addUser(addUserDTO);
		return user;
	}
}
