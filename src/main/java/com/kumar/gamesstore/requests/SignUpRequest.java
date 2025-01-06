package com.kumar.gamesstore.requests;

import com.kumar.gamesstore.domain.UserRole;

import lombok.Data;

@Data
public class SignUpRequest {
	
	
	private String fullName;
	private String email;
	private String otp;
	private long mobile;
}
