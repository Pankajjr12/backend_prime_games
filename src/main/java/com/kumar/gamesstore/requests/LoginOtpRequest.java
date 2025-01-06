package com.kumar.gamesstore.requests;

import com.kumar.gamesstore.domain.UserRole;

import lombok.Data;
@Data
public class LoginOtpRequest {

	private String email;
	private String otp;
	private UserRole role;
}
