package com.kumar.gamesstore.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kumar.gamesstore.domain.OrderStatus;
import com.kumar.gamesstore.domain.PaymentStatus;
import com.kumar.gamesstore.modals.Address;
import com.kumar.gamesstore.modals.PaymentDetails;

public class OrderDto {

    private Long id;
    private String orderId;
    private UserDto user;
    private Long sellerId;
    private List<OrderItemDto> orderItems = new ArrayList<>();
    private Address shippingAddress;
    private PaymentDetails paymentDetails = new PaymentDetails();
    private double totalMrpPrice;
    private Integer totalSellingPrice;
    private Integer discount;
    private OrderStatus orderStatus;
    private int totalItem;
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    private LocalDateTime orderDate = LocalDateTime.now();
    private LocalDateTime deliverDate = orderDate.plusDays(7);

    // No-argument constructor
    public OrderDto() {
    }

    // All-argument constructor
    public OrderDto(Long id, String orderId, UserDto user, Long sellerId, List<OrderItemDto> orderItems, Address shippingAddress,
            PaymentDetails paymentDetails, double totalMrpPrice, Integer totalSellingPrice, Integer discount,
            OrderStatus orderStatus, int totalItem, PaymentStatus paymentStatus, LocalDateTime orderDate, LocalDateTime deliverDate) {
        this.id = id;
        this.orderId = orderId;
        this.user = user;
        this.sellerId = sellerId;
        this.orderItems = orderItems;
        this.shippingAddress = shippingAddress;
        this.paymentDetails = paymentDetails;
        this.totalMrpPrice = totalMrpPrice;
        this.totalSellingPrice = totalSellingPrice;
        this.discount = discount;
        this.orderStatus = orderStatus;
        this.totalItem = totalItem;
        this.paymentStatus = paymentStatus;
        this.orderDate = orderDate;
        this.deliverDate = deliverDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public double getTotalMrpPrice() {
        return totalMrpPrice;
    }

    public void setTotalMrpPrice(double totalMrpPrice) {
        this.totalMrpPrice = totalMrpPrice;
    }

    public Integer getTotalSellingPrice() {
        return totalSellingPrice;
    }

    public void setTotalSellingPrice(Integer totalSellingPrice) {
        this.totalSellingPrice = totalSellingPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(LocalDateTime deliverDate) {
        this.deliverDate = deliverDate;
    }

    // toString method
    @Override
    public String toString() {
        return "OrderDto{"
                + "id=" + id
                + ", orderId='" + orderId + '\''
                + ", user=" + user
                + ", sellerId=" + sellerId
                + ", orderItems=" + orderItems
                + ", shippingAddress=" + shippingAddress
                + ", paymentDetails=" + paymentDetails
                + ", totalMrpPrice=" + totalMrpPrice
                + ", totalSellingPrice=" + totalSellingPrice
                + ", discount=" + discount
                + ", orderStatus=" + orderStatus
                + ", totalItem=" + totalItem
                + ", paymentStatus=" + paymentStatus
                + ", orderDate=" + orderDate
                + ", deliverDate=" + deliverDate
                + '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderDto)) {
            return false;
        }

        OrderDto orderDto = (OrderDto) o;

        if (Double.compare(orderDto.totalMrpPrice, totalMrpPrice) != 0) {
            return false;
        }
        if (totalItem != orderDto.totalItem) {
            return false;
        }
        if (id != null ? !id.equals(orderDto.id) : orderDto.id != null) {
            return false;
        }
        if (orderId != null ? !orderId.equals(orderDto.orderId) : orderDto.orderId != null) {
            return false;
        }
        if (user != null ? !user.equals(orderDto.user) : orderDto.user != null) {
            return false;
        }
        if (sellerId != null ? !sellerId.equals(orderDto.sellerId) : orderDto.sellerId != null) {
            return false;
        }
        if (orderItems != null ? !orderItems.equals(orderDto.orderItems) : orderDto.orderItems != null) {
            return false;
        }
        if (shippingAddress != null ? !shippingAddress.equals(orderDto.shippingAddress) : orderDto.shippingAddress != null) {
            return false;
        }
        if (paymentDetails != null ? !paymentDetails.equals(orderDto.paymentDetails) : orderDto.paymentDetails != null) {
            return false;
        }
        if (totalSellingPrice != null ? !totalSellingPrice.equals(orderDto.totalSellingPrice) : orderDto.totalSellingPrice != null) {
            return false;
        }
        if (discount != null ? !discount.equals(orderDto.discount) : orderDto.discount != null) {
            return false;
        }
        if (orderStatus != orderDto.orderStatus) {
            return false;
        }
        if (paymentStatus != orderDto.paymentStatus) {
            return false;
        }
        if (orderDate != null ? !orderDate.equals(orderDto.orderDate) : orderDto.orderDate != null) {
            return false;
        }
        return deliverDate != null ? deliverDate.equals(orderDto.deliverDate) : orderDto.deliverDate == null;
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (sellerId != null ? sellerId.hashCode() : 0);
        result = 31 * result + (orderItems != null ? orderItems.hashCode() : 0);
        result = 31 * result + (shippingAddress != null ? shippingAddress.hashCode() : 0);
        result = 31 * result + (paymentDetails != null ? paymentDetails.hashCode() : 0);
        result = 31 * result + (totalMrpPrice != +0.0d ? Double.hashCode(totalMrpPrice) : 0);
        result = 31 * result + (totalSellingPrice != null ? totalSellingPrice.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + totalItem;
        result = 31 * result + (paymentStatus != null ? paymentStatus.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (deliverDate != null ? deliverDate.hashCode() : 0);
        return result;
    }
}
