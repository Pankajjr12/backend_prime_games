package com.kumar.gamesstore.dto;

public class OrderItemDto {

    private Long id;
    private ProductDto product;
    private String year;
    private int quantity;
    private Integer mrpPrice;
    private Integer sellingPrice;
    private Long userId;

    // No-argument constructor
    public OrderItemDto() {
    }

    // All-argument constructor
    public OrderItemDto(Long id, ProductDto product, String year, int quantity, Integer mrpPrice, Integer sellingPrice, Long userId) {
        this.id = id;
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

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
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

    // toString method
    @Override
    public String toString() {
        return "OrderItemDto{"
                + "id=" + id
                + ", product=" + product
                + ", year='" + year + '\''
                + ", quantity=" + quantity
                + ", mrpPrice=" + mrpPrice
                + ", sellingPrice=" + sellingPrice
                + ", userId=" + userId
                + '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderItemDto)) {
            return false;
        }

        OrderItemDto that = (OrderItemDto) o;

        if (quantity != that.quantity) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (product != null ? !product.equals(that.product) : that.product != null) {
            return false;
        }
        if (year != null ? !year.equals(that.year) : that.year != null) {
            return false;
        }
        if (mrpPrice != null ? !mrpPrice.equals(that.mrpPrice) : that.mrpPrice != null) {
            return false;
        }
        if (sellingPrice != null ? !sellingPrice.equals(that.sellingPrice) : that.sellingPrice != null) {
            return false;
        }
        return userId != null ? userId.equals(that.userId) : that.userId == null;
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (mrpPrice != null ? mrpPrice.hashCode() : 0);
        result = 31 * result + (sellingPrice != null ? sellingPrice.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
