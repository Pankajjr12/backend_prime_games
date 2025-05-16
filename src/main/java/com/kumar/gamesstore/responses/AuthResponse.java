package com.kumar.gamesstore.responses;

import com.kumar.gamesstore.domain.UserRole;

public class AuthResponse {

    private String jwt;
    private String message;
    private UserRole role;

    // No-arg constructor
    public AuthResponse() {
    }

    // All-args constructor
    public AuthResponse(String jwt, String message, UserRole role) {
        this.jwt = jwt;
        this.message = message;
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthResponse{"
                + "jwt='" + jwt + '\''
                + ", message='" + message + '\''
                + ", role=" + role
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuthResponse)) {
            return false;
        }

        AuthResponse that = (AuthResponse) o;

        if (jwt != null ? !jwt.equals(that.jwt) : that.jwt != null) {
            return false;
        }
        if (message != null ? !message.equals(that.message) : that.message != null) {
            return false;
        }
        return role == that.role;
    }

    @Override
    public int hashCode() {
        int result = jwt != null ? jwt.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
