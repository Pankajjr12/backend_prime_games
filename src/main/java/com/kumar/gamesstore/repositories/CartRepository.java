package com.kumar.gamesstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Cart findByUserId(Long userId);

}
