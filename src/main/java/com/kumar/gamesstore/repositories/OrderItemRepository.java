package com.kumar.gamesstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
