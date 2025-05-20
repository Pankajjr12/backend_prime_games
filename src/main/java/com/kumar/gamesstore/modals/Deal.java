package com.kumar.gamesstore.modals;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer discount;

    @OneToOne
    private HomeCategory homeCategory;

    // No-argument constructor
    public Deal() {
    }

    // All-argument constructor
    public Deal(Long id, Integer discount, HomeCategory homeCategory) {
        this.id = id;
        this.discount = discount;
        this.homeCategory = homeCategory;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public HomeCategory getHomeCategory() {
        return homeCategory;
    }

    public void setHomeCategory(HomeCategory homeCategory) {
        this.homeCategory = homeCategory;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deal)) {
            return false;
        }

        Deal deal = (Deal) o;

        if (id != null ? !id.equals(deal.id) : deal.id != null) {
            return false;
        }
        if (discount != null ? !discount.equals(deal.discount) : deal.discount != null) {
            return false;
        }
        return homeCategory != null ? homeCategory.equals(deal.homeCategory) : deal.homeCategory == null;
    }

    // hashCode
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (homeCategory != null ? homeCategory.hashCode() : 0);
        return result;
    }

    // toString
    @Override
    public String toString() {
        return "Deal{"
                + "id=" + id
                + ", discount=" + discount
                + ", homeCategory=" + (homeCategory != null ? homeCategory.getId() : null)
                + '}';
    }
}
