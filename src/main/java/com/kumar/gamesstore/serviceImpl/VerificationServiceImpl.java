package com.kumar.gamesstore.serviceImpl;

import org.springframework.stereotype.Service;

import com.kumar.gamesstore.modals.VerificationCode;
import com.kumar.gamesstore.repositories.VerificationRepository;
import com.kumar.gamesstore.services.VerificationService;

@Service
public class VerificationServiceImpl implements VerificationService {

	
	   private final VerificationRepository verificationCodeRepository;

	    VerificationServiceImpl(VerificationRepository verificationCodeRepository){

	        this.verificationCodeRepository = verificationCodeRepository;
	    }

	    @Override
	    public VerificationCode createVerificationCode(String otp,String email) {
	        VerificationCode isExist=verificationCodeRepository.findByEmail(email);

	        if(isExist!=null) {
	            verificationCodeRepository.delete(isExist);
	        }

	        VerificationCode verificationCode=new VerificationCode();
	        verificationCode.setOtp(otp);
	        verificationCode.setEmail(email);

	        return verificationCodeRepository.save(verificationCode);

	    }
}
