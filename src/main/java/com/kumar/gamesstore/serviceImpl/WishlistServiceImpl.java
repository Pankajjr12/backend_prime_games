package com.kumar.gamesstore.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kumar.gamesstore.exceptions.WishlistNotFoundException;
import com.kumar.gamesstore.modals.Product;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.modals.WishList;
import com.kumar.gamesstore.repositories.WishListRepository;
import com.kumar.gamesstore.services.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishListRepository wishlistRepository;

    @Autowired
    public WishlistServiceImpl(WishListRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public WishList createWishlist(User user) {

        WishList wishlist = new WishList();
        wishlist.setUser(user);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public WishList getWishlistByUserId(User user) {
        WishList wishlist = wishlistRepository.findByUserId(user.getId());
        if (wishlist == null) {
            wishlist = this.createWishlist(user);
        }
        return wishlist;
    }

    @Override
    public WishList addProductToWishlist(User user, Product product) throws WishlistNotFoundException {

        WishList wishlist = this.getWishlistByUserId(user);
        if (wishlist.getProducts().contains(product)) {
            wishlist.getProducts().remove(product);
        } else {
            wishlist.getProducts().add(product);
        }

        return wishlistRepository.save(wishlist);
    }

}
