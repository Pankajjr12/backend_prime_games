package com.kumar.gamesstore.serviceImpl;

import org.springframework.stereotype.Service;

import com.kumar.gamesstore.config.JwtProvider;
import com.kumar.gamesstore.exceptions.UserException;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.repositories.UserRepository;
import com.kumar.gamesstore.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	private final JwtProvider jwtProvider;

	@Override
	public User findUserByJwtToken(String jwt) throws UserException {
		// TODO Auto-generated method stub
		
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		return this.findUserByEmail(email);
	}

	@Override
	public User findUserByEmail(String email) throws UserException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);
		
		if(user ==null) {
			throw new UserException("User not found with email - "+email);
		}
		return user;
	}

}
