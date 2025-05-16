package com.kumar.gamesstore.requests;

import java.util.List;

import jakarta.persistence.Column;

public class CreateProductRequest {

    private String title;

    @Column(length = 2000)
    private String description;

    private int mrpPrice;
    private int sellingPrice;
    private String brand;

    private double numRatings;

    @Column(length = 5000)
    private List<String> images;

    private String category;
    private String category2;
    private String category3;

    private String years;
    private String platform;

    // No-argument constructor
    public CreateProductRequest() {
    }

    // All-argument constructor
    public CreateProductRequest(String title, String description, int mrpPrice, int sellingPrice, String brand,
            double numRatings, List<String> images, String category, String category2, String category3,
            String years, String platform) {
        this.title = title;
        this.description = description;
        this.mrpPrice = mrpPrice;
        this.sellingPrice = sellingPrice;
        this.brand = brand;
        this.numRatings = numRatings;
        this.images = images;
        this.category = category;
        this.category2 = category2;
        this.category3 = category3;
        this.years = years;
        this.platform = platform;
    }

    // Getter and setter for 'title'
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and setter for 'description'
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and setter for 'mrpPrice'
    public int getMrpPrice() {
        return mrpPrice;
    }

    public void setMrpPrice(int mrpPrice) {
        this.mrpPrice = mrpPrice;
    }

    // Getter and setter for 'sellingPrice'
    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    // Getter and setter for 'brand'
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Getter and setter for 'numRatings'
    public double getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(double numRatings) {
        this.numRatings = numRatings;
    }

    // Getter and setter for 'images'
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    // Getter and setter for 'category'
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter and setter for 'category2'
    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    // Getter and setter for 'category3'
    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    // Getter and setter for 'years'
    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    // Getter and setter for 'platform'
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "CreateProductRequest{"
                + "title='" + title + '\''
                + ", description='" + description + '\''
                + ", mrpPrice=" + mrpPrice
                + ", sellingPrice=" + sellingPrice
                + ", brand='" + brand + '\''
                + ", numRatings=" + numRatings
                + ", images=" + images
                + ", category='" + category + '\''
                + ", category2='" + category2 + '\''
                + ", category3='" + category3 + '\''
                + ", years='" + years + '\''
                + ", platform='" + platform + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreateProductRequest)) {
            return false;
        }

        CreateProductRequest that = (CreateProductRequest) o;

        if (mrpPrice != that.mrpPrice) {
            return false;
        }
        if (sellingPrice != that.sellingPrice) {
            return false;
        }
        if (Double.compare(that.numRatings, numRatings) != 0) {
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
        if (category != null ? !category.equals(that.category) : that.category != null) {
            return false;
        }
        if (category2 != null ? !category2.equals(that.category2) : that.category2 != null) {
            return false;
        }
        if (category3 != null ? !category3.equals(that.category3) : that.category3 != null) {
            return false;
        }
        if (years != null ? !years.equals(that.years) : that.years != null) {
            return false;
        }
        return platform != null ? platform.equals(that.platform) : that.platform == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + mrpPrice;
        result = 31 * result + sellingPrice;
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        temp = Double.doubleToLongBits(numRatings);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (category2 != null ? category2.hashCode() : 0);
        result = 31 * result + (category3 != null ? category3.hashCode() : 0);
        result = 31 * result + (years != null ? years.hashCode() : 0);
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        return result;
    }
}
