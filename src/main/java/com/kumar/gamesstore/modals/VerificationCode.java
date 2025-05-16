package com.kumar.gamesstore.modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String otp;

    private String email;

    @OneToOne
    private User user;

    @OneToOne
    private Seller seller;

    // No-arg constructor
    public VerificationCode() {
    }

    // All-arg constructor
    public VerificationCode(Long id, String otp, String email, User user, Seller seller) {
        this.id = id;
        this.otp = otp;
        this.email = email;
        this.user = user;
        this.seller = seller;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VerificationCode)) {
            return false;
        }

        VerificationCode that = (VerificationCode) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (otp != null ? !otp.equals(that.otp) : that.otp != null) {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        if (user != null ? !user.equals(that.user) : that.user != null) {
            return false;
        }
        return seller != null ? seller.equals(that.seller) : that.seller == null;
    }

    // hashCode
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (otp != null ? otp.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (seller != null ? seller.hashCode() : 0);
        return result;
    }

    // toString
    @Override
    public String toString() {
        return "VerificationCode{"
                + "id=" + id
                + ", otp='" + otp + '\''
                + ", email='" + email + '\''
                + ", user=" + user
                + ", seller=" + seller
                + '}';
    }
}
