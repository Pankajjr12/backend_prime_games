package com.kumar.gamesstore.services;

import java.util.List;
import java.util.Set;

import com.kumar.gamesstore.domain.OrderStatus;
import com.kumar.gamesstore.exceptions.OrderException;
import com.kumar.gamesstore.modals.Address;
import com.kumar.gamesstore.modals.Cart;
import com.kumar.gamesstore.modals.Order;
import com.kumar.gamesstore.modals.User;

public interface OrderService {

	
	public Set<Order> createOrder(User user, Address shippingAddress, Cart cart);
	
	public Order findOrderById(Long orderId) throws OrderException;
	
	public List<Order> usersOrderHistory(Long userId);
	
	public List<Order>getShopsOrders(Long sellerId);

	public Order updateOrderStatus(Long orderId,
								   OrderStatus orderStatus)
			throws OrderException;
	
	public void deleteOrder(Long orderId) throws OrderException;

	Order cancelOrder(Long orderId,User user) throws OrderException;
}
