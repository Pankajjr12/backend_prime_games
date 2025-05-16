package com.kumar.gamesstore.modals;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String reviewText;

    @Column(nullable = false)
    private double rating;

    @ElementCollection
    private List<String> productImages;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // No-arg constructor
    public Review() {
    }

    // All-arg constructor
    public Review(Long id, String reviewText, double rating, List<String> productImages, Product product, User user, LocalDateTime createdAt) {
        this.id = id;
        this.reviewText = reviewText;
        this.rating = rating;
        this.productImages = productImages;
        this.product = product;
        this.user = user;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<String> productImages) {
        this.productImages = productImages;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

        Review review = (Review) o;

        if (Double.compare(review.rating, rating) != 0) {
            return false;
        }
        if (id != null ? !id.equals(review.id) : review.id != null) {
            return false;
        }
        if (reviewText != null ? !reviewText.equals(review.reviewText) : review.reviewText != null) {
            return false;
        }
        if (productImages != null ? !productImages.equals(review.productImages) : review.productImages != null) {
            return false;
        }
        if (product != null ? !product.equals(review.product) : review.product != null) {
            return false;
        }
        if (user != null ? !user.equals(review.user) : review.user != null) {
            return false;
        }
        return createdAt != null ? createdAt.equals(review.createdAt) : review.createdAt == null;
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (reviewText != null ? reviewText.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (productImages != null ? productImages.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    // toString method
    @Override
    public String toString() {
        return "Review{"
                + "id=" + id
                + ", reviewText='" + reviewText + '\''
                + ", rating=" + rating
                + ", productImages=" + productImages
                + ", product=" + product
                + ", user=" + user
                + ", createdAt=" + createdAt
                + '}';
    }
}
