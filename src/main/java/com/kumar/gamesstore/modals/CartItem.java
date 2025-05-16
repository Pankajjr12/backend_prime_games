package com.kumar.gamesstore.modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    private Product product;

    private String year;

    private int quantity = 1;

    private Integer mrpPrice;

    private Integer sellingPrice;

    private Long userId;

    // No-arg constructor
    public CartItem() {
    }

    // All-arg constructor
    public CartItem(Long id, Cart cart, Product product, String year, int quantity, Integer mrpPrice, Integer sellingPrice, Long userId) {
        this.id = id;
        this.cart = cart;
        this.product = product;
        this.year = year;
        this.quantity = quantity;
        this.mrpPrice = mrpPrice;
        this.sellingPrice = sellingPrice;
        this.userId = userId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getMrpPrice() {
        return mrpPrice;
    }

    public void setMrpPrice(Integer mrpPrice) {
        this.mrpPrice = mrpPrice;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // toString
    @Override
    public String toString() {
        return "CartItem{"
                + "id=" + id
                + ", cart=" + (cart != null ? cart.getId() : null)
                + ", product=" + (product != null ? product.getId() : null)
                + ", year='" + year + '\''
                + ", quantity=" + quantity
                + ", mrpPrice=" + mrpPrice
                + ", sellingPrice=" + sellingPrice
                + ", userId=" + userId
                + '}';
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartItem)) {
            return false;
        }

        CartItem cartItem = (CartItem) o;

        if (quantity != cartItem.quantity) {
            return false;
        }
        if (id != null ? !id.equals(cartItem.id) : cartItem.id != null) {
            return false;
        }
        if (cart != null ? !cart.equals(cartItem.cart) : cartItem.cart != null) {
            return false;
        }
        if (product != null ? !product.equals(cartItem.product) : cartItem.product != null) {
            return false;
        }
        if (year != null ? !year.equals(cartItem.year) : cartItem.year != null) {
            return false;
        }
        if (mrpPrice != null ? !mrpPrice.equals(cartItem.mrpPrice) : cartItem.mrpPrice != null) {
            return false;
        }
        if (sellingPrice != null ? !sellingPrice.equals(cartItem.sellingPrice) : cartItem.sellingPrice != null) {
            return false;
        }
        return userId != null ? userId.equals(cartItem.userId) : cartItem.userId == null;
    }

    // hashCode
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cart != null ? cart.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (mrpPrice != null ? mrpPrice.hashCode() : 0);
        result = 31 * result + (sellingPrice != null ? sellingPrice.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
