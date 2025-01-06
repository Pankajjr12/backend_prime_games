package com.kumar.gamesstore.services;

import com.kumar.gamesstore.exceptions.UserException;
import com.kumar.gamesstore.modals.User;

public interface UserService {

	
	public User findUserByJwtToken(String jwt) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;
}
