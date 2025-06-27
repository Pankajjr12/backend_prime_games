package com.kumar.gamesstore.modals;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    private double totalSellingPrice;
    private int totalItem;
    private int totalMrpPrice;
    private int discount;
    private String couponCode;
    private int couponPrice;

    public Cart() {
    }

    public Cart(Long id, User user, Set<CartItem> cartItems, double totalSellingPrice, int totalItem, int totalMrpPrice, int discount, String couponCode, int couponPrice) {
        this.id = id;
        this.user = user;
        this.cartItems = cartItems;
        this.totalSellingPrice = totalSellingPrice;
        this.totalItem = totalItem;
        this.totalMrpPrice = totalMrpPrice;
        this.discount = discount;
        this.couponCode = couponCode;
        this.couponPrice = couponPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalSellingPrice() {
        return totalSellingPrice;
    }

    public void setTotalSellingPrice(double totalSellingPrice) {
        this.totalSellingPrice = totalSellingPrice;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalMrpPrice() {
        return totalMrpPrice;
    }

    public void setTotalMrpPrice(int totalMrpPrice) {
        this.totalMrpPrice = totalMrpPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public int getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(int couponPrice) {
        this.couponPrice = couponPrice;
    }

    @Override
    public String toString() {
        return "Cart{"
                + "id=" + id
                + ", user=" + user
                + ", totalSellingPrice=" + totalSellingPrice
                + ", totalItem=" + totalItem
                + ", totalMrpPrice=" + totalMrpPrice
                + ", discount=" + discount
                + ", couponCode='" + couponCode + '\''
                + ", couponPrice=" + couponPrice
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cart)) {
            return false;
        }
        Cart cart = (Cart) o;
        return id != null && id.equals(cart.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
