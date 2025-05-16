package com.kumar.gamesstore.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDto {

    private Long id;
    private String title;
    private String description;
    private int mrpPrice;
    private int sellingPrice;
    private int discountPercent;
    private int quantity;
    private String brand;
    private List<String> images = new ArrayList<>();
    private int numRatings;
    private LocalDateTime createdAt;
    private String years;

    // No-argument constructor
    public ProductDto() {
    }

    // All-argument constructor
    public ProductDto(Long id, String title, String description, int mrpPrice, int sellingPrice, int discountPercent,
            int quantity, String brand, List<String> images, int numRatings, LocalDateTime createdAt, String years) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.mrpPrice = mrpPrice;
        this.sellingPrice = sellingPrice;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.brand = brand;
        this.images = images;
        this.numRatings = numRatings;
        this.createdAt = createdAt;
        this.years = years;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMrpPrice() {
        return mrpPrice;
    }

    public void setMrpPrice(int mrpPrice) {
        this.mrpPrice = mrpPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    // toString method
    @Override
    public String toString() {
        return "ProductDto{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", description='" + description + '\''
                + ", mrpPrice=" + mrpPrice
                + ", sellingPrice=" + sellingPrice
                + ", discountPercent=" + discountPercent
                + ", quantity=" + quantity
                + ", brand='" + brand + '\''
                + ", images=" + images
                + ", numRatings=" + numRatings
                + ", createdAt=" + createdAt
                + ", years='" + years + '\''
                + '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDto)) {
            return false;
        }

        ProductDto that = (ProductDto) o;

        if (mrpPrice != that.mrpPrice) {
            return false;
        }
        if (sellingPrice != that.sellingPrice) {
            return false;
        }
        if (discountPercent != that.discountPercent) {
            return false;
        }
        if (quantity != that.quantity) {
            return false;
        }
        if (numRatings != that.numRatings) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (title != null ? !title.equals(that.title) : that.title != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) {
            return false;
        }
        if (images != null ? !images.equals(that.images) : that.images != null) {
            return false;
        }
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) {
            return false;
        }
        return years != null ? years.equals(that.years) : that.years == null;
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + mrpPrice;
        result = 31 * result + sellingPrice;
        result = 31 * result + discountPercent;
        result = 31 * result + quantity;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + numRatings;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (years != null ? years.hashCode() : 0);
        return result;
    }
}
