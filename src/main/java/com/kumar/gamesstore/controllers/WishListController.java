package com.kumar.gamesstore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kumar.gamesstore.exceptions.ProductException;
import com.kumar.gamesstore.exceptions.UserException;
import com.kumar.gamesstore.exceptions.WishlistNotFoundException;
import com.kumar.gamesstore.modals.Product;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.modals.WishList;
import com.kumar.gamesstore.services.ProductService;
import com.kumar.gamesstore.services.UserService;
import com.kumar.gamesstore.services.WishlistService;

@RestController
@RequestMapping("/api/wishlist")

public class WishListController {

    private final WishlistService wishlistService;
    private final ProductService productService;
    private final UserService userService;

    public WishListController(WishlistService wishlistService, ProductService productService, UserService userService) {
        this.wishlistService = wishlistService;
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<WishList> createWishlist(@RequestBody User user) {
        WishList wishlist = wishlistService.createWishlist(user);
        return ResponseEntity.ok(wishlist);
    }

    @GetMapping()
    public ResponseEntity<WishList> getWishlistByUserId(
            @RequestHeader("Authorization") String jwt) throws UserException {

        User user = userService.findUserByJwtToken(jwt);
        WishList wishlist = wishlistService.getWishlistByUserId(user);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<WishList> addProductToWishlist(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt) throws WishlistNotFoundException, ProductException, UserException {

        Product product = productService.findProductById(productId);
        User user = userService.findUserByJwtToken(jwt);
        WishList updatedWishlist = wishlistService.addProductToWishlist(
                user,
                product
        );
        return ResponseEntity.ok(updatedWishlist);

    }
}
