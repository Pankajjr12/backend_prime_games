package com.kumar.gamesstore.services;

import java.util.List;

import com.kumar.gamesstore.domain.AccountStatus;
import com.kumar.gamesstore.exceptions.SellerException;
import com.kumar.gamesstore.modals.Seller;

public interface SellerService {
	
	Seller getSellerProfile(String jwt) throws SellerException;
	
	Seller createSeller(Seller seller) throws SellerException;
	
	Seller getSellerById(Long id) throws SellerException;
	
	Seller getSellerByEmail(String email) throws SellerException;
	
	Seller getSellerByMobile(Long mobile) throws SellerException;
	
	List<Seller> getAllSellers(AccountStatus status);
	
	Seller updateSeller(Long id, Seller seller) throws SellerException, Exception;
	
	void deleteSeller(Long id) throws Exception;
	
	Seller verifyEmail(String email,String otp) throws SellerException;
	
	Seller verifyPhone(Long mobile,String otp) throws SellerException;
	
	Seller updateSellerAccountStatus(Long id,AccountStatus accountStatus) throws SellerException;
	

}
