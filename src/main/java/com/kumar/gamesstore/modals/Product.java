package com.kumar.gamesstore.modals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private int sellingPrice;

    private int mrpPrice;

    private int discountPercent;

    private int quantity;

    private String brand;

    private String platform;

    @ElementCollection
    private List<String> images = new ArrayList<>();

    private double numRatings;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Seller seller;

    private LocalDateTime createdAt;

    private String years;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    private boolean inStock = true;

    // No-arg constructor
    public Product() {
    }

    // All-arg constructor
    public Product(Long id, String title, String description, int sellingPrice, int mrpPrice, int discountPercent,
            int quantity, String brand, String platform, List<String> images, double numRatings, Category category,
            Seller seller, LocalDateTime createdAt, String years, List<Review> reviews, boolean inStock) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sellingPrice = sellingPrice;
        this.mrpPrice = mrpPrice;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.brand = brand;
        this.platform = platform;
        this.images = images;
        this.numRatings = numRatings;
        this.category = category;
        this.seller = seller;
        this.createdAt = createdAt;
        this.years = years;
        this.reviews = reviews;
        this.inStock = inStock;
    }

    // Getters and setters
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

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getMrpPrice() {
        return mrpPrice;
    }

    public void setMrpPrice(int mrpPrice) {
        this.mrpPrice = mrpPrice;
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public double getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(double numRatings) {
        this.numRatings = numRatings;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }

        Product product = (Product) o;

        if (sellingPrice != product.sellingPrice) {
            return false;
        }
        if (mrpPrice != product.mrpPrice) {
            return false;
        }
        if (discountPercent != product.discountPercent) {
            return false;
        }
        if (quantity != product.quantity) {
            return false;
        }
        if (Double.compare(product.numRatings, numRatings) != 0) {
            return false;
        }
        if (inStock != product.inStock) {
            return false;
        }
        if (id != null ? !id.equals(product.id) : product.id != null) {
            return false;
        }
        if (title != null ? !title.equals(product.title) : product.title != null) {
            return false;
        }
        if (description != null ? !description.equals(product.description) : product.description != null) {
            return false;
        }
        if (brand != null ? !brand.equals(product.brand) : product.brand != null) {
            return false;
        }
        if (platform != null ? !platform.equals(product.platform) : product.platform != null) {
            return false;
        }
        if (images != null ? !images.equals(product.images) : product.images != null) {
            return false;
        }
        if (category != null ? !category.equals(product.category) : product.category != null) {
            return false;
        }
        if (seller != null ? !seller.equals(product.seller) : product.seller != null) {
            return false;
        }
        if (createdAt != null ? !createdAt.equals(product.createdAt) : product.createdAt != null) {
            return false;
        }
        return years != null ? years.equals(product.years) : product.years == null;
    }

    // hashCode
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + sellingPrice;
        result = 31 * result + mrpPrice;
        result = 31 * result + discountPercent;
        result = 31 * result + quantity;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (int) Double.doubleToLongBits(numRatings);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (seller != null ? seller.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (years != null ? years.hashCode() : 0);
        result = 31 * result + (inStock ? 1 : 0);
        return result;
    }

    // toString
    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", description='" + description + '\''
                + ", sellingPrice=" + sellingPrice
                + ", mrpPrice=" + mrpPrice
                + ", discountPercent=" + discountPercent
                + ", quantity=" + quantity
                + ", brand='" + brand + '\''
                + ", platform='" + platform + '\''
                + ", images=" + images
                + ", numRatings=" + numRatings
                + ", category=" + category
                + ", seller=" + seller
                + ", createdAt=" + createdAt
                + ", years='" + years + '\''
                + ", reviews=" + reviews
                + ", inStock=" + inStock
                + '}';
    }
}
