package com.codekages.service;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codekages.dao.UserDAO;
import com.codekages.model.User;

@Service
public class AuthorizationService {

	@Autowired
	private UserDAO userDao;
	
	public User login(String username, String password) throws LoginException {
		User user = userDao.getUserByUsernameAndPassword(username, password);
		
		if (user == null) {
			throw new LoginException("Incorrect credentials provided");
		}
		
		return user;
	}
	
}
