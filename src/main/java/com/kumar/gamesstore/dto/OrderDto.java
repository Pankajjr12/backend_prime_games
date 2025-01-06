package com.kumar.gamesstore.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kumar.gamesstore.domain.OrderStatus;
import com.kumar.gamesstore.domain.PaymentStatus;
import com.kumar.gamesstore.modals.Address;
import com.kumar.gamesstore.modals.PaymentDetails;

import lombok.Data;

@Data
public class OrderDto {

    private Long id;

    private String orderId;

    private UserDto user;

    private Long sellerId;

    private List<OrderItemDto> orderItems = new ArrayList<>();

    private Address shippingAddress;

    private PaymentDetails paymentDetails=new PaymentDetails();

    private double totalMrpPrice;

    private Integer totalSellingPrice;

    private Integer discount;

    private OrderStatus orderStatus;

    private int totalItem;

    private PaymentStatus paymentStatus=PaymentStatus.PENDING;

    private LocalDateTime orderDate = LocalDateTime.now();
    private LocalDateTime deliverDate = orderDate.plusDays(7);

}
