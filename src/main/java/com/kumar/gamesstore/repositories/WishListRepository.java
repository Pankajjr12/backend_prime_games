package com.kumar.gamesstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    WishList findByUserId(Long userId);
}
