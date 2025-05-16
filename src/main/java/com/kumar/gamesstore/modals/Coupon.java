package com.kumar.gamesstore.modals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private double discountPercentage;

    private LocalDate validityStartDate;

    private LocalDate validityEndDate;

    private double minimumOrderValue;

    private boolean isActive = true;

    @ManyToMany(mappedBy = "usedCoupons")
    private Set<User> usedByUsers = new HashSet<>();

    // No-arg constructor
    public Coupon() {
    }

    // All-arg constructor
    public Coupon(Long id, String code, double discountPercentage, LocalDate validityStartDate, LocalDate validityEndDate,
            double minimumOrderValue, boolean isActive, Set<User> usedByUsers) {
        this.id = id;
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.validityStartDate = validityStartDate;
        this.validityEndDate = validityEndDate;
        this.minimumOrderValue = minimumOrderValue;
        this.isActive = isActive;
        this.usedByUsers = usedByUsers;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDate getValidityStartDate() {
        return validityStartDate;
    }

    public void setValidityStartDate(LocalDate validityStartDate) {
        this.validityStartDate = validityStartDate;
    }

    public LocalDate getValidityEndDate() {
        return validityEndDate;
    }

    public void setValidityEndDate(LocalDate validityEndDate) {
        this.validityEndDate = validityEndDate;
    }

    public double getMinimumOrderValue() {
        return minimumOrderValue;
    }

    public void setMinimumOrderValue(double minimumOrderValue) {
        this.minimumOrderValue = minimumOrderValue;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<User> getUsedByUsers() {
        return usedByUsers;
    }

    public void setUsedByUsers(Set<User> usedByUsers) {
        this.usedByUsers = usedByUsers;
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

        Coupon coupon = (Coupon) o;

        if (Double.compare(coupon.discountPercentage, discountPercentage) != 0) {
            return false;
        }
        if (Double.compare(coupon.minimumOrderValue, minimumOrderValue) != 0) {
            return false;
        }
        if (isActive != coupon.isActive) {
            return false;
        }
        if (id != null ? !id.equals(coupon.id) : coupon.id != null) {
            return false;
        }
        if (code != null ? !code.equals(coupon.code) : coupon.code != null) {
            return false;
        }
        if (validityStartDate != null ? !validityStartDate.equals(coupon.validityStartDate) : coupon.validityStartDate != null) {
            return false;
        }
        return validityEndDate != null ? validityEndDate.equals(coupon.validityEndDate) : coupon.validityEndDate == null;
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        temp = Double.doubleToLongBits(discountPercentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (validityStartDate != null ? validityStartDate.hashCode() : 0);
        result = 31 * result + (validityEndDate != null ? validityEndDate.hashCode() : 0);
        temp = Double.doubleToLongBits(minimumOrderValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (usedByUsers != null ? usedByUsers.hashCode() : 0);
        return result;
    }

    // toString method
    @Override
    public String toString() {
        return "Coupon{"
                + "id=" + id
                + ", code='" + code + '\''
                + ", discountPercentage=" + discountPercentage
                + ", validityStartDate=" + validityStartDate
                + ", validityEndDate=" + validityEndDate
                + ", minimumOrderValue=" + minimumOrderValue
                + ", isActive=" + isActive
                + ", usedByUsers=" + usedByUsers
                + '}';
    }
}
