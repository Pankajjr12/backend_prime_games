package com.kumar.gamesstore.services;

import com.kumar.gamesstore.exceptions.CartItemException;
import com.kumar.gamesstore.exceptions.UserException;
import com.kumar.gamesstore.modals.CartItem;


public interface CartItemService {

	public CartItem updateCartItem(Long userId, Long id,CartItem cartItem) throws CartItemException, UserException;
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
