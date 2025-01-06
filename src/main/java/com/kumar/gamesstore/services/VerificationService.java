package com.kumar.gamesstore.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.VerificationCode;

public interface VerificationService {

    VerificationCode createVerificationCode(String otp, String email);
}