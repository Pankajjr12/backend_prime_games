package com.kumar.gamesstore.services;


import com.kumar.gamesstore.domain.UserRole;
import com.kumar.gamesstore.requests.LoginRequest;
import com.kumar.gamesstore.requests.SignUpRequest;
import com.kumar.gamesstore.responses.AuthResponse;


public interface AuthService {

	
	void sentLoginOtp(String email, UserRole role) throws Exception;
	String createUser(SignUpRequest req) throws Exception;
	
	AuthResponse login(LoginRequest req) throws Exception;
}
