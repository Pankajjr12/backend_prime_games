package com.kumar.gamesstore.responses;

import com.kumar.gamesstore.dto.OrderHistory;
import com.kumar.gamesstore.modals.Cart;
import com.kumar.gamesstore.modals.Product;

public class FunctionResponse {

    private String functionName;
    private Cart userCart;
    private OrderHistory orderHistory;
    private Product product;

    // No-arg constructor
    public FunctionResponse() {
    }

    // All-args constructor
    public FunctionResponse(String functionName, Cart userCart, OrderHistory orderHistory, Product product) {
        this.functionName = functionName;
        this.userCart = userCart;
        this.orderHistory = orderHistory;
        this.product = product;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Cart getUserCart() {
        return userCart;
    }

    public void setUserCart(Cart userCart) {
        this.userCart = userCart;
    }

    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "FunctionResponse{"
                + "functionName='" + functionName + '\''
                + ", userCart=" + userCart
                + ", orderHistory=" + orderHistory
                + ", product=" + product
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FunctionResponse)) {
            return false;
        }

        FunctionResponse that = (FunctionResponse) o;

        if (functionName != null ? !functionName.equals(that.functionName) : that.functionName != null) {
            return false;
        }
        if (userCart != null ? !userCart.equals(that.userCart) : that.userCart != null) {
            return false;
        }
        if (orderHistory != null ? !orderHistory.equals(that.orderHistory) : that.orderHistory != null) {
            return false;
        }
        return product != null ? product.equals(that.product) : that.product == null;
    }

    @Override
    public int hashCode() {
        int result = functionName != null ? functionName.hashCode() : 0;
        result = 31 * result + (userCart != null ? userCart.hashCode() : 0);
        result = 31 * result + (orderHistory != null ? orderHistory.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }
}
