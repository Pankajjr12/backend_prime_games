package com.kumar.gamesstore.requests;

import com.kumar.gamesstore.domain.UserRole;

public class LoginOtpRequest {

    private String email;
    private String otp;
    private UserRole role;

    // No-argument constructor
    public LoginOtpRequest() {
    }

    // All-argument constructor
    public LoginOtpRequest(String email, String otp, UserRole role) {
        this.email = email;
        this.otp = otp;
        this.role = role;
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

    // Getter and setter for 'role'
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginOtpRequest{"
                + "email='" + email + '\''
                + ", otp='" + otp + '\''
                + ", role=" + role
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoginOtpRequest)) {
            return false;
        }

        LoginOtpRequest that = (LoginOtpRequest) o;

        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        if (otp != null ? !otp.equals(that.otp) : that.otp != null) {
            return false;
        }
        return role == that.role;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (otp != null ? otp.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
