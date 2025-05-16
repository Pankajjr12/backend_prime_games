package com.kumar.gamesstore.modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @ManyToMany
    private Set<Product> products = new HashSet<>();

    // No-arg constructor
    public WishList() {
    }

    // All-arg constructor
    public WishList(Long id, User user, Set<Product> products) {
        this.id = id;
        this.user = user;
        this.products = products;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WishList)) {
            return false;
        }

        WishList wishList = (WishList) o;

        if (id != null ? !id.equals(wishList.id) : wishList.id != null) {
            return false;
        }
        if (user != null ? !user.equals(wishList.user) : wishList.user != null) {
            return false;
        }
        return products != null ? products.equals(wishList.products) : wishList.products == null;
    }

    // hashCode
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }

    // toString
    @Override
    public String toString() {
        return "WishList{"
                + "id=" + id
                + ", user=" + user
                + ", products=" + products
                + '}';
    }
}
