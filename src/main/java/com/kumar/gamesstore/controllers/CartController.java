package com.kumar.gamesstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kumar.gamesstore.exceptions.CartItemException;
import com.kumar.gamesstore.exceptions.ProductException;
import com.kumar.gamesstore.exceptions.UserException;
import com.kumar.gamesstore.modals.Cart;
import com.kumar.gamesstore.modals.CartItem;
import com.kumar.gamesstore.modals.Product;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.requests.AddItemRequest;
import com.kumar.gamesstore.responses.ApiResponse;
import com.kumar.gamesstore.services.CartItemService;
import com.kumar.gamesstore.services.CartService;
import com.kumar.gamesstore.services.ProductService;
import com.kumar.gamesstore.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
	
	private final CartService cartService;
	private final UserService userService;
	private final ProductService productService;
	private final CartItemService cartItemService;
	
	@GetMapping
	public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user=userService.findUserByJwtToken(jwt);
		
		Cart cart=cartService.findUserCart(user);
		
		System.out.println("cart - "+cart.getUser().getEmail());
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	@PutMapping("/add")
	public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req,
												  @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		
		User user=userService.findUserByJwtToken(jwt);
		Product product=productService.findProductById(req.getProductId());
		
		CartItem item = cartService.addCartItem(user,
				product,
				req.getYear(),
				req.getQuantity());
		
		ApiResponse res = new ApiResponse();
		res.setMessage("Item Added to Cart Successfully.");

		return new ResponseEntity<>(item,HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/item/{cartItemId}")
	public ResponseEntity<ApiResponse>deleteCartItemHandler(
			@PathVariable Long cartItemId,
			@RequestHeader("Authorization")String jwt)
			throws CartItemException, UserException{

		User user=userService.findUserByJwtToken(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);

		ApiResponse res=new ApiResponse("Item Remove From Cart",true);

		return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/item/{cartItemId}")
	public ResponseEntity<CartItem>updateCartItemHandler(
			@PathVariable Long cartItemId,
			@RequestBody CartItem cartItem,
			@RequestHeader("Authorization")String jwt)
			throws CartItemException, UserException{

		User user=userService.findUserByJwtToken(jwt);

		CartItem updatedCartItem = null;
		if(cartItem.getQuantity()>0){
			updatedCartItem=cartItemService.updateCartItem(user.getId(),
					cartItemId, cartItem);
		}
		return new ResponseEntity<>(updatedCartItem,HttpStatus.ACCEPTED);
	}
}
