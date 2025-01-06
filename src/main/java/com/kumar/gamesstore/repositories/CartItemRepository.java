package com.kumar.gamesstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.Cart;
import com.kumar.gamesstore.modals.CartItem;
import com.kumar.gamesstore.modals.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	CartItem findByCartAndProductAndYear(Cart cart, Product product, String year);

}
