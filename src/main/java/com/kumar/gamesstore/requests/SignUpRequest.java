package com.kumar.gamesstore.requests;

public class SignUpRequest {

    private String fullName;
    private String email;
    private String otp;
    private long mobile;

    // No-arg constructor
    public SignUpRequest() {
    }

    // All-args constructor
    public SignUpRequest(String fullName, String email, String otp, long mobile) {
        this.fullName = fullName;
        this.email = email;
        this.otp = otp;
        this.mobile = mobile;
    }

    // Getters and setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "SignUpRequest{"
                + "fullName='" + fullName + '\''
                + ", email='" + email + '\''
                + ", otp='" + otp + '\''
                + ", mobile=" + mobile
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SignUpRequest)) {
            return false;
        }

        SignUpRequest that = (SignUpRequest) o;

        if (mobile != that.mobile) {
            return false;
        }
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        return otp != null ? otp.equals(that.otp) : that.otp == null;
    }

    @Override
    public int hashCode() {
        int result = fullName != null ? fullName.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (otp != null ? otp.hashCode() : 0);
        result = 31 * result + (int) (mobile ^ (mobile >>> 32));
        return result;
    }
}
