package com.kumar.gamesstore.services;

import com.kumar.gamesstore.modals.OrderItem;

public interface OrderItemService {

	OrderItem getOrderItemById(Long id) throws Exception;
}
