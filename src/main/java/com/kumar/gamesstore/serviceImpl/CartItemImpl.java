package com.kumar.gamesstore.serviceImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kumar.gamesstore.exceptions.CartItemException;
import com.kumar.gamesstore.exceptions.UserException;
import com.kumar.gamesstore.modals.CartItem;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.repositories.CartItemRepository;
import com.kumar.gamesstore.services.CartItemService;

@Service
public class CartItemImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItemById(id);
        User cartItemUser = item.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {

            item.setQuantity(cartItem.getQuantity());
            item.setMrpPrice(item.getQuantity() * item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity() * item.getProduct().getSellingPrice());

            return cartItemRepository.save(item);

        } else {
            throw new CartItemException("You can't update  another users cart_item");
        }
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        // TODO Auto-generated method stub

        System.out.println("userId- " + userId + " cartItemId " + cartItemId);

        CartItem cartItem = findCartItemById(cartItemId);

        User cartItemUser = cartItem.getCart().getUser();

        if (cartItemUser.getId().equals(userId)) {
            cartItemRepository.deleteById(cartItem.getId());
        } else {
            throw new UserException("you can't remove anothor users item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {

        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new CartItemException("cartItem not found with id : " + cartItemId);
    }

}
