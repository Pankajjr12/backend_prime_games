package com.kumar.gamesstore.serviceImpl;

import org.springframework.stereotype.Service;

import com.kumar.gamesstore.exceptions.ProductException;
import com.kumar.gamesstore.modals.Cart;
import com.kumar.gamesstore.modals.CartItem;
import com.kumar.gamesstore.modals.Product;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.repositories.CartItemRepository;
import com.kumar.gamesstore.repositories.CartRepository;
import com.kumar.gamesstore.services.CartService;
import com.kumar.gamesstore.services.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final ProductService productService;
	
	
	public static int calculateDiscountPercentage(double mrpPrice, double sellingPrice) {
		if (mrpPrice <= 0) {
			return 0;
		}
		double discount = mrpPrice - sellingPrice;
		double discountPercentage = (discount / mrpPrice) * 100;
		return (int) discountPercentage;
	}
	
	@Override
	public CartItem addCartItem(User user, Product product, String year, int quantity) throws ProductException {
		// TODO Auto-generated method stub
		
        Cart cart=findUserCart(user);
		
		CartItem isPresent=cartItemRepository.findByCartAndProductAndYear(
				cart, product, year);
		
		if(isPresent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);

			cartItem.setQuantity(quantity);
			cartItem.setUserId(user.getId());
			
			int totalPrice=quantity*product.getSellingPrice();
			cartItem.setSellingPrice(totalPrice);
			cartItem.setMrpPrice(quantity*product.getMrpPrice());
			cartItem.setYear(year);

			cart.getCartItems().add(cartItem);
			cartItem.setCart(cart);

            return cartItemRepository.save(cartItem);
		}

		return isPresent;
	}

	@Override
	public Cart findUserCart(User user) {
		Cart cart =	cartRepository.findByUserId(user.getId());

		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		for(CartItem cartsItem : cart.getCartItems()) {
			totalPrice+=cartsItem.getMrpPrice();
			totalDiscountedPrice+=cartsItem.getSellingPrice();
			totalItem+=cartsItem.getQuantity();
		}

		cart.setTotalMrpPrice(totalPrice);
		cart.setTotalItem(cart.getCartItems().size());
		cart.setTotalSellingPrice(totalDiscountedPrice-cart.getCouponPrice());
		cart.setDiscount(calculateDiscountPercentage(totalPrice,totalDiscountedPrice));
		cart.setTotalItem(totalItem);
		
		return cartRepository.save(cart);
	}

}
