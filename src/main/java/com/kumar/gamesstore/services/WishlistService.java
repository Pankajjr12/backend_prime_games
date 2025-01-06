package com.kumar.gamesstore.services;

import com.kumar.gamesstore.exceptions.WishlistNotFoundException;
import com.kumar.gamesstore.modals.Product;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.modals.WishList;

public interface WishlistService {

    WishList createWishlist(User user);

    WishList getWishlistByUserId(User user);

    WishList addProductToWishlist(User user, Product product) throws WishlistNotFoundException;
}
