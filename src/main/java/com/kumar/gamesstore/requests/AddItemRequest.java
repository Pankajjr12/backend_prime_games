package com.kumar.gamesstore.requests;

public class AddItemRequest {

    private Long productId;
    private String year;
    private int quantity;
    private Integer price;

    // No-argument constructor
    public AddItemRequest() {
    }

    // All-argument constructor
    public AddItemRequest(Long productId, String year, int quantity, Integer price) {
        this.productId = productId;
        this.year = year;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter and setter for 'productId'
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    // Getter and setter for 'year'
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    // Getter and setter for 'quantity'
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter and setter for 'price'
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "AddItemRequest{"
                + "productId=" + productId
                + ", year='" + year + '\''
                + ", quantity=" + quantity
                + ", price=" + price
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddItemRequest)) {
            return false;
        }

        AddItemRequest that = (AddItemRequest) o;

        if (quantity != that.quantity) {
            return false;
        }
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) {
            return false;
        }
        if (year != null ? !year.equals(that.year) : that.year != null) {
            return false;
        }
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
