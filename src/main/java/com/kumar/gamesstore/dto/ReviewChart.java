package com.kumar.gamesstore.dto;

public class ReviewChart {

    private String date;
    private double revenue;

    // No-argument constructor
    public ReviewChart() {
    }

    // All-argument constructor
    public ReviewChart(String date, double revenue) {
        this.date = date;
        this.revenue = revenue;
    }

    // Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    // toString method
    @Override
    public String toString() {
        return "ReviewChart{"
                + "date='" + date + '\''
                + ", revenue=" + revenue
                + '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReviewChart)) {
            return false;
        }

        ReviewChart that = (ReviewChart) o;

        if (Double.compare(that.revenue, revenue) != 0) {
            return false;
        }
        return date != null ? date.equals(that.date) : that.date == null;
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = date != null ? date.hashCode() : 0;
        temp = Double.doubleToLongBits(revenue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
