package com.kumar.gamesstore.modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class SellerReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Seller seller;

    private Long totalEarnings = 0L;

    private Long totalSales = 0L;

    private Long totalRefunds = 0L;

    private Long totalTax = 0L;

    private Long netEarnings = 0L;

    private Integer totalOrders = 0;

    private Integer cancelOrders = 0;

    private Integer totalTransactions = 0;

    // No-arg constructor
    public SellerReport() {
    }

    // All-arg constructor
    public SellerReport(Long id, Seller seller, Long totalEarnings, Long totalSales, Long totalRefunds, Long totalTax,
            Long netEarnings, Integer totalOrders, Integer cancelOrders, Integer totalTransactions) {
        this.id = id;
        this.seller = seller;
        this.totalEarnings = totalEarnings;
        this.totalSales = totalSales;
        this.totalRefunds = totalRefunds;
        this.totalTax = totalTax;
        this.netEarnings = netEarnings;
        this.totalOrders = totalOrders;
        this.cancelOrders = cancelOrders;
        this.totalTransactions = totalTransactions;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Long getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Long totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public Long getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Long totalSales) {
        this.totalSales = totalSales;
    }

    public Long getTotalRefunds() {
        return totalRefunds;
    }

    public void setTotalRefunds(Long totalRefunds) {
        this.totalRefunds = totalRefunds;
    }

    public Long getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Long totalTax) {
        this.totalTax = totalTax;
    }

    public Long getNetEarnings() {
        return netEarnings;
    }

    public void setNetEarnings(Long netEarnings) {
        this.netEarnings = netEarnings;
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Integer getCancelOrders() {
        return cancelOrders;
    }

    public void setCancelOrders(Integer cancelOrders) {
        this.cancelOrders = cancelOrders;
    }

    public Integer getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(Integer totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SellerReport)) {
            return false;
        }

        SellerReport that = (SellerReport) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (seller != null ? !seller.equals(that.seller) : that.seller != null) {
            return false;
        }
        if (totalEarnings != null ? !totalEarnings.equals(that.totalEarnings) : that.totalEarnings != null) {
            return false;
        }
        if (totalSales != null ? !totalSales.equals(that.totalSales) : that.totalSales != null) {
            return false;
        }
        if (totalRefunds != null ? !totalRefunds.equals(that.totalRefunds) : that.totalRefunds != null) {
            return false;
        }
        if (totalTax != null ? !totalTax.equals(that.totalTax) : that.totalTax != null) {
            return false;
        }
        if (netEarnings != null ? !netEarnings.equals(that.netEarnings) : that.netEarnings != null) {
            return false;
        }
        if (totalOrders != null ? !totalOrders.equals(that.totalOrders) : that.totalOrders != null) {
            return false;
        }
        if (cancelOrders != null ? !cancelOrders.equals(that.cancelOrders) : that.cancelOrders != null) {
            return false;
        }
        return totalTransactions != null ? totalTransactions.equals(that.totalTransactions) : that.totalTransactions == null;
    }

    // hashCode
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (seller != null ? seller.hashCode() : 0);
        result = 31 * result + (totalEarnings != null ? totalEarnings.hashCode() : 0);
        result = 31 * result + (totalSales != null ? totalSales.hashCode() : 0);
        result = 31 * result + (totalRefunds != null ? totalRefunds.hashCode() : 0);
        result = 31 * result + (totalTax != null ? totalTax.hashCode() : 0);
        result = 31 * result + (netEarnings != null ? netEarnings.hashCode() : 0);
        result = 31 * result + (totalOrders != null ? totalOrders.hashCode() : 0);
        result = 31 * result + (cancelOrders != null ? cancelOrders.hashCode() : 0);
        result = 31 * result + (totalTransactions != null ? totalTransactions.hashCode() : 0);
        return result;
    }

    // toString
    @Override
    public String toString() {
        return "SellerReport{"
                + "id=" + id
                + ", seller=" + seller
                + ", totalEarnings=" + totalEarnings
                + ", totalSales=" + totalSales
                + ", totalRefunds=" + totalRefunds
                + ", totalTax=" + totalTax
                + ", netEarnings=" + netEarnings
                + ", totalOrders=" + totalOrders
                + ", cancelOrders=" + cancelOrders
                + ", totalTransactions=" + totalTransactions
                + '}';
    }
}
