package com.codekages.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codekages.dto.AddUserDTO;
import com.codekages.model.User;

@Repository
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public User getUserByUsernameAndPassword(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		
		try {			
			User user = (User) session.createQuery("FROM User u WHERE u.username=:username AND u.password=:password")
					.setParameter("username", username)
					.setParameter("password", password)
					.getSingleResult();
			
			return user;
		} catch (NoResultException e) {
			return null;
		}
		
	}

	@Transactional
	public User addUser(AddUserDTO addUserDTO) {
		Session session = sessionFactory.getCurrentSession();
		
		User newUser = new User(addUserDTO.getUsername(), addUserDTO.getPassword(), addUserDTO.getFirstName(), addUserDTO.getLastName());
		session.persist(newUser);
		
		return newUser;
	}
}
