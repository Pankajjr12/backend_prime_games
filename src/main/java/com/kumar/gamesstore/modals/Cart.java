package com.kumar.gamesstore.modals;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.HashSet;
import java.util.Set;

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

    // No-argument constructor
    public Cart() {
    }

    // All-argument constructor
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

    // Getters and Setters
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

    // toString method
    @Override
    public String toString() {
        return "Cart{"
                + "id=" + id
                + ", user=" + user
                + ", cartItems=" + cartItems
                + ", totalSellingPrice=" + totalSellingPrice
                + ", totalItem=" + totalItem
                + ", totalMrpPrice=" + totalMrpPrice
                + ", discount=" + discount
                + ", couponCode='" + couponCode + '\''
                + ", couponPrice=" + couponPrice
                + '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cart cart = (Cart) o;

        if (Double.compare(cart.totalSellingPrice, totalSellingPrice) != 0) {
            return false;
        }
        if (totalItem != cart.totalItem) {
            return false;
        }
        if (totalMrpPrice != cart.totalMrpPrice) {
            return false;
        }
        if (discount != cart.discount) {
            return false;
        }
        if (couponPrice != cart.couponPrice) {
            return false;
        }
        if (id != null ? !id.equals(cart.id) : cart.id != null) {
            return false;
        }
        if (user != null ? !user.equals(cart.user) : cart.user != null) {
            return false;
        }
        if (cartItems != null ? !cartItems.equals(cart.cartItems) : cart.cartItems != null) {
            return false;
        }
        return couponCode != null ? couponCode.equals(cart.couponCode) : cart.couponCode == null;
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (cartItems != null ? cartItems.hashCode() : 0);
        temp = Double.doubleToLongBits(totalSellingPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + totalItem;
        result = 31 * result + totalMrpPrice;
        result = 31 * result + discount;
        result = 31 * result + (couponCode != null ? couponCode.hashCode() : 0);
        result = 31 * result + couponPrice;
        return result;
    }
}
