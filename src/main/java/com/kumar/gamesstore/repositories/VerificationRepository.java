package com.kumar.gamesstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.VerificationCode;

public interface VerificationRepository extends JpaRepository<VerificationCode, Long> {
	
	  VerificationCode findByEmail(String email);
	    VerificationCode findByOtp(String otp);
	

}
