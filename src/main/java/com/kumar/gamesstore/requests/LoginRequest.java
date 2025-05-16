package com.kumar.gamesstore.requests;

public class LoginRequest {

    private String email;
    private String otp;

    // No-arg constructor
    public LoginRequest() {
    }

    // All-args constructor
    public LoginRequest(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    // Getter and setter for 'email'
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and setter for 'otp'
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "LoginRequest{"
                + "email='" + email + '\''
                + ", otp='" + otp + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoginRequest)) {
            return false;
        }

        LoginRequest that = (LoginRequest) o;

        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        return otp != null ? otp.equals(that.otp) : that.otp == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (otp != null ? otp.hashCode() : 0);
        return result;
    }
}
