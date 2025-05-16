package com.kumar.gamesstore.serviceImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kumar.gamesstore.exceptions.OrderException;
import com.kumar.gamesstore.modals.OrderItem;
import com.kumar.gamesstore.repositories.OrderItemRepository;
import com.kumar.gamesstore.services.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderItem getOrderItemById(Long id) throws Exception {

        System.out.println("------- " + id);
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (orderItem.isPresent()) {
            return orderItem.get();
        }
        throw new OrderException("Order item not found");
    }

}
