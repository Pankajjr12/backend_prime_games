package com.kumar.gamesstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.domain.AccountStatus;
import com.kumar.gamesstore.modals.Seller;



public interface SellerRepository extends JpaRepository<Seller, Long> {

	Seller findByEmail(String email);
	
	Seller findByMobile(Long mobile);
	


	List<Seller> findByAccountStatus(AccountStatus accountStatus);
}
