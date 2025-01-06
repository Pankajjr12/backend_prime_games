package com.kumar.gamesstore.serviceImpl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kumar.gamesstore.config.JwtProvider;
import com.kumar.gamesstore.domain.AccountStatus;
import com.kumar.gamesstore.domain.UserRole;
import com.kumar.gamesstore.exceptions.SellerException;
import com.kumar.gamesstore.modals.Address;
import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.repositories.AddressRepository;
import com.kumar.gamesstore.repositories.SellerRepository;
import com.kumar.gamesstore.services.SellerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService{
	
	
	private final SellerRepository sellerRepository;
	
	private final AddressRepository addressRepository;
	
	private final JwtProvider jwtProvider;
	
	private final PasswordEncoder passwordEncoder;

	  
	@Override
	    
	public Seller getSellerProfile(String jwt) throws SellerException {
	        
		String email = jwtProvider.getEmailFromJwtToken(jwt);
	        
		return this.getSellerByEmail(email);
	    
	}

	@Override
	public Seller createSeller(Seller seller) throws SecurityException {
		// TODO Auto-generated method stub
		Seller sellerExists = sellerRepository.findByEmail(seller.getEmail());
		
		if(sellerExists!=null) {
			throw new SecurityException("Seller already exists, please used different email.");
		}
		
		Address savedAddress = addressRepository.save(seller.getPickupAddress());
		
		Seller newSeller = new Seller();
		
		newSeller.setEmail(seller.getEmail());
		newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
		newSeller.setSellerName(seller.getSellerName());
		newSeller.setPickupAddress(savedAddress);
		newSeller.setGSTIN(seller.getGSTIN());
		newSeller.setRole(UserRole.ROLE_SELLER);
		newSeller.setMobile(seller.getMobile());
		newSeller.setBankDetails(seller.getBankDetails());
		newSeller.setBusinessDetails(seller.getBusinessDetails());
		
		
		return sellerRepository.save(newSeller);
	}

	@Override
	public Seller getSellerById(Long id) throws SellerException {
		// TODO Auto-generated method stub
		return sellerRepository.findById(id).orElseThrow(()-> new SellerException("User id not exists." + id));
	}

	@Override
	public Seller getSellerByEmail(String email) throws SecurityException {
		
		Seller seller = sellerRepository.findByEmail(email);
		
		if(seller == null) {
			throw new SecurityException("Seller not found..");
		}
		return seller;
	}

	@Override
	public List<Seller> getAllSellers(AccountStatus status) {
		// TODO Auto-generated method stub
		return sellerRepository.findByAccountStatus(status);
	}

	@Override
	public Seller updateSeller(Long id, Seller seller) throws Exception {
		// TODO Auto-generated method stub
		
		Seller existingSeller = this.getSellerById(id);
		
		if(seller.getSellerName() != null) {
			existingSeller.setSellerName(seller.getSellerName());
		}
		
		if(seller.getMobile() != 0) {
			existingSeller.setMobile(seller.getMobile());
		}
		
		if(seller.getEmail() != null) {
			existingSeller.setEmail(seller.getEmail());
		}
		
		if(seller.getBusinessDetails() != null && seller.getBusinessDetails().getBusinessName() != null) {
			existingSeller.getBusinessDetails().setBusinessName(seller.getBusinessDetails().getBusinessName());
		}
		
		if(seller.getBankDetails() != null && seller.getBankDetails().getAccountHolderName() != null && seller.getBankDetails().getIfsCode() != null 
				&& seller.getBankDetails().getAccountNumber() != null)
		{
			existingSeller.getBankDetails().setAccountHolderName(seller.getBankDetails().getAccountHolderName());
			existingSeller.getBankDetails().setAccountNumber(seller.getBankDetails().getAccountNumber());
			existingSeller.getBankDetails().setIfsCode(seller.getBankDetails().getIfsCode());
		}
		
		if(seller.getPickupAddress() != null && seller.getPickupAddress().getAddress() != null
				&& seller.getPickupAddress().getMobile() != null && seller.getPickupAddress().getCity() != null
				&& seller.getPickupAddress().getState() != null) {
			
			existingSeller.getPickupAddress().setAddress(seller.getPickupAddress().getAddress());
			existingSeller.getPickupAddress().setMobile(seller.getPickupAddress().getMobile());
			existingSeller.getPickupAddress().setCity(seller.getPickupAddress().getCity());
			existingSeller.getPickupAddress().setState(seller.getPickupAddress().getState());
			
		}
		
		if(seller.getGSTIN() != null) {
			existingSeller.setGSTIN(seller.getGSTIN());
		}
		
		return sellerRepository.save(existingSeller);
	}

	@Override
	public void deleteSeller(Long id) throws Exception {
		// TODO Auto-generated method stub
		
		Seller seller = getSellerById(id);
		sellerRepository.delete(seller);
		
	}

	@Override
	public Seller verifyEmail(String email, String otp) throws SecurityException{
		
		Seller seller = this.getSellerByEmail(email);
		seller.setEmailVerified(true);
		
		return sellerRepository.save(seller);
	}

	@Override
	public Seller verifyPhone(Long mobile, String otp)  throws SecurityException {
		
		Seller seller = getSellerByMobile(mobile);
		seller.setPhoneVerified(true);
        return sellerRepository.save(seller);
	}

	@Override
	public Seller updateSellerAccountStatus(Long id, AccountStatus accountStatus) throws  SellerException {
		// TODO Auto-generated method stub
		Seller seller = this.getSellerById(id);
		seller.setAccountStatus(accountStatus);
		
		return sellerRepository.save(seller);
	}


	@Override
	public Seller getSellerByMobile(Long mobile) throws SecurityException {
		Seller seller = sellerRepository.findByMobile(mobile);
		
		if(seller == null) {
			throw new SecurityException("Seller not found..");
		}
		return seller;
	}

}
