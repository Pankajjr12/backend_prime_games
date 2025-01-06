package com.kumar.gamesstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kumar.gamesstore.domain.UserRole;
import com.kumar.gamesstore.repositories.UserRepository;
import com.kumar.gamesstore.requests.LoginOtpRequest;
import com.kumar.gamesstore.requests.LoginRequest;
import com.kumar.gamesstore.requests.SignUpRequest;
import com.kumar.gamesstore.responses.ApiResponse;
import com.kumar.gamesstore.responses.AuthResponse;
import com.kumar.gamesstore.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignUpRequest req) throws Exception {
		 
		String jwt = authService.createUser(req);
		
		AuthResponse res = new AuthResponse();
		
		res.setJwt(jwt);
		res.setMessage("Register Successfully");
		 res.setRole(UserRole.ROLE_CUSTOMER);
		
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/sent/login-signup-otp")
	public ResponseEntity<ApiResponse> sentOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {
		
		
		authService.sentLoginOtp(req.getEmail(),req.getRole());
		
		ApiResponse res = new ApiResponse();
		
		res.setMessage("OTP sent successfully");
	
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {
		
		
		AuthResponse response = authService.login(req);
				
		return ResponseEntity.ok(response);
	}
}
