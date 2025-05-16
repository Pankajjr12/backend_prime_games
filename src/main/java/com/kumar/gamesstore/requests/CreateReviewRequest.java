package com.kumar.gamesstore.requests;

import java.util.List;

public class CreateReviewRequest {

    private String reviewText;
    private double reviewRating;
    private List<String> productImages;

    // No-argument constructor
    public CreateReviewRequest() {
    }

    // All-argument constructor
    public CreateReviewRequest(String reviewText, double reviewRating, List<String> productImages) {
        this.reviewText = reviewText;
        this.reviewRating = reviewRating;
        this.productImages = productImages;
    }

    // Getter and setter for 'reviewText'
    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    // Getter and setter for 'reviewRating'
    public double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(double reviewRating) {
        this.reviewRating = reviewRating;
    }

    // Getter and setter for 'productImages'
    public List<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<String> productImages) {
        this.productImages = productImages;
    }

    @Override
    public String toString() {
        return "CreateReviewRequest{"
                + "reviewText='" + reviewText + '\''
                + ", reviewRating=" + reviewRating
                + ", productImages=" + productImages
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateReviewRequest)) {
            return false;
        }

        CreateReviewRequest that = (CreateReviewRequest) o;

        if (Double.compare(that.reviewRating, reviewRating) != 0) {
            return false;
        }
        if (reviewText != null ? !reviewText.equals(that.reviewText) : that.reviewText != null) {
            return false;
        }
        return productImages != null ? productImages.equals(that.productImages) : that.productImages == null;
    }

    @Override
    public int hashCode() {
        int result = reviewText != null ? reviewText.hashCode() : 0;
        result = 31 * result + (reviewRating != +0.0d ? Double.hashCode(reviewRating) : 0);
        result = 31 * result + (productImages != null ? productImages.hashCode() : 0);
        return result;
    }
}
