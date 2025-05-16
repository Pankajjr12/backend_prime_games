package com.kumar.gamesstore.responses;

public class ApiResponse {

    private String message;
    private boolean status;

    public ApiResponse() {
        // No-arg constructor
    }

    public ApiResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ApiResponse{"
                + "message='" + message + '\''
                + ", status=" + status
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiResponse)) {
            return false;
        }

        ApiResponse that = (ApiResponse) o;

        if (status != that.status) {
            return false;
        }
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (status ? 1 : 0);
        return result;
    }
}
