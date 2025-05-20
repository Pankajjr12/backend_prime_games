package com.kumar.gamesstore.modals;

import java.io.Serializable;
import java.util.List;

public class Home implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<HomeCategory> grid;
    private List<HomeCategory> shopByCategories;
    private List<HomeCategory> gameCategories;
    private List<HomeCategory> dealCategories;
    private List<Deal> deals;

    // No-argument constructor
    public Home() {
    }

    // All-argument constructor
    public Home(List<HomeCategory> grid, List<HomeCategory> shopByCategories,
            List<HomeCategory> gameCategories, List<HomeCategory> dealCategories,
            List<Deal> deals) {
        this.grid = grid;
        this.shopByCategories = shopByCategories;
        this.gameCategories = gameCategories;
        this.dealCategories = dealCategories;
        this.deals = deals;
    }

    // Getters and Setters
    public List<HomeCategory> getGrid() {
        return grid;
    }

    public void setGrid(List<HomeCategory> grid) {
        this.grid = grid;
    }

    public List<HomeCategory> getShopByCategories() {
        return shopByCategories;
    }

    public void setShopByCategories(List<HomeCategory> shopByCategories) {
        this.shopByCategories = shopByCategories;
    }

    public List<HomeCategory> getGameCategories() {
        return gameCategories;
    }

    public void setGameCategories(List<HomeCategory> gameCategories) {
        this.gameCategories = gameCategories;
    }

    public List<HomeCategory> getDealCategories() {
        return dealCategories;
    }

    public void setDealCategories(List<HomeCategory> dealCategories) {
        this.dealCategories = dealCategories;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    // toString
    @Override
    public String toString() {
        return "Home{"
                + "grid=" + grid
                + ", shopByCategories=" + shopByCategories
                + ", gameCategories=" + gameCategories
                + ", dealCategories=" + dealCategories
                + ", deals=" + deals
                + '}';
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Home)) {
            return false;
        }

        Home home = (Home) o;

        if (grid != null ? !grid.equals(home.grid) : home.grid != null) {
            return false;
        }
        if (shopByCategories != null ? !shopByCategories.equals(home.shopByCategories) : home.shopByCategories != null) {
            return false;
        }
        if (gameCategories != null ? !gameCategories.equals(home.gameCategories) : home.gameCategories != null) {
            return false;
        }
        if (dealCategories != null ? !dealCategories.equals(home.dealCategories) : home.dealCategories != null) {
            return false;
        }
        return deals != null ? deals.equals(home.deals) : home.deals == null;
    }

    // hashCode
    @Override
    public int hashCode() {
        int result = grid != null ? grid.hashCode() : 0;
        result = 31 * result + (shopByCategories != null ? shopByCategories.hashCode() : 0);
        result = 31 * result + (gameCategories != null ? gameCategories.hashCode() : 0);
        result = 31 * result + (dealCategories != null ? dealCategories.hashCode() : 0);
        result = 31 * result + (deals != null ? deals.hashCode() : 0);
        return result;
    }
}
