package com.kumar.gamesstore.requests;

import lombok.Data;

@Data
public class LoginRequest {

	private String email;
	private String otp;
}
