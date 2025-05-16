package com.kumar.gamesstore.modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FooterImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imageUrl;
    private String title;

    // No-argument constructor
    public FooterImage() {
    }

    // All-argument constructor
    public FooterImage(Long id, String imageUrl, String title) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FooterImage)) {
            return false;
        }

        FooterImage that = (FooterImage) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null) {
            return false;
        }
        return title != null ? title.equals(that.title) : that.title == null;
    }

    // hashCode
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    // toString
    @Override
    public String toString() {
        return "FooterImage{"
                + "id=" + id
                + ", imageUrl='" + imageUrl + '\''
                + ", title='" + title + '\''
                + '}';
    }
}
