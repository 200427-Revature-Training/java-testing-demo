package com.revature.services;

import com.revature.daos.UserDao;
import com.revature.exceptions.HttpStatusException;
import com.revature.models.User;

public class UserService {

	private UserDao userDao;
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * Service method which processes input user data and validates that the data
	 * is valid prior to database insertion
	 */
	public User insertUser(User user) {
		// Email Validation
		if(user.getEmailAddress() == null) {
			throw new HttpStatusException(422);
		}
		
		if(user.getEmailAddress().length() < 4 || user.getEmailAddress().length() > 255) {
			throw new HttpStatusException(422);
		}
		
		// validate email address format
		
		// username validation
		if(user.getUsername() == null) {
			throw new HttpStatusException(422);
		}
		
		if(user.getUsername().length() < 6 || user.getUsername().length() > 30) {
			throw new HttpStatusException(422);
		}
	
		return userDao.insertUser(user);
	}
	
}
