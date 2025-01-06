package com.kumar.gamesstore.services;

import com.kumar.gamesstore.exceptions.ProductException;
import com.kumar.gamesstore.modals.Cart;
import com.kumar.gamesstore.modals.CartItem;
import com.kumar.gamesstore.modals.Product;
import com.kumar.gamesstore.modals.User;

public interface CartService {

	public CartItem addCartItem(User user,
			Product product,
			String year,
			int quantity) throws ProductException;
	
	public Cart findUserCart(User user);
}
