package com.kumar.gamesstore.responses;

import com.kumar.gamesstore.domain.UserRole;

import lombok.Data;

@Data
public class AuthResponse {

	
	private String jwt;
	private String message;
	private UserRole role;
}
