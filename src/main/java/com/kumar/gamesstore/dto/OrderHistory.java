package com.kumar.gamesstore.dto;

import java.util.List;

public class OrderHistory {

    private Long id;
    private UserDto user;
    private List<OrderDto> currentOrders;
    private int totalOrders;
    private int cancelledOrders;
    private int completedOrders;
    private int pendingOrders;

    // No-argument constructor
    public OrderHistory() {
    }

    // All-argument constructor
    public OrderHistory(Long id, UserDto user, List<OrderDto> currentOrders, int totalOrders, int cancelledOrders,
            int completedOrders, int pendingOrders) {
        this.id = id;
        this.user = user;
        this.currentOrders = currentOrders;
        this.totalOrders = totalOrders;
        this.cancelledOrders = cancelledOrders;
        this.completedOrders = completedOrders;
        this.pendingOrders = pendingOrders;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<OrderDto> getCurrentOrders() {
        return currentOrders;
    }

    public void setCurrentOrders(List<OrderDto> currentOrders) {
        this.currentOrders = currentOrders;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public int getCancelledOrders() {
        return cancelledOrders;
    }

    public void setCancelledOrders(int cancelledOrders) {
        this.cancelledOrders = cancelledOrders;
    }

    public int getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(int completedOrders) {
        this.completedOrders = completedOrders;
    }

    public int getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(int pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    // toString method
    @Override
    public String toString() {
        return "OrderHistory{"
                + "id=" + id
                + ", user=" + user
                + ", currentOrders=" + currentOrders
                + ", totalOrders=" + totalOrders
                + ", cancelledOrders=" + cancelledOrders
                + ", completedOrders=" + completedOrders
                + ", pendingOrders=" + pendingOrders
                + '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderHistory)) {
            return false;
        }

        OrderHistory that = (OrderHistory) o;

        if (totalOrders != that.totalOrders) {
            return false;
        }
        if (cancelledOrders != that.cancelledOrders) {
            return false;
        }
        if (completedOrders != that.completedOrders) {
            return false;
        }
        if (pendingOrders != that.pendingOrders) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (user != null ? !user.equals(that.user) : that.user != null) {
            return false;
        }
        return currentOrders != null ? currentOrders.equals(that.currentOrders) : that.currentOrders == null;
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (currentOrders != null ? currentOrders.hashCode() : 0);
        result = 31 * result + totalOrders;
        result = 31 * result + cancelledOrders;
        result = 31 * result + completedOrders;
        result = 31 * result + pendingOrders;
        return result;
    }
}
