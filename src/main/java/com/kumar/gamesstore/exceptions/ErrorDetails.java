package com.kumar.gamesstore.exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {

    private String error;
    private String details;
    private LocalDateTime timeStamp;

    // No-argument constructor
    public ErrorDetails() {
    }

    // All-argument constructor
    public ErrorDetails(String error, String details, LocalDateTime timeStamp) {
        this.error = error;
        this.details = details;
        this.timeStamp = timeStamp;
    }

    // Getter and setter for 'error'
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    // Getter and setter for 'details'
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    // Getter and setter for 'timeStamp'
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "ErrorDetails{"
                + "error='" + error + '\''
                + ", details='" + details + '\''
                + ", timeStamp=" + timeStamp
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ErrorDetails)) {
            return false;
        }

        ErrorDetails that = (ErrorDetails) o;

        if (error != null ? !error.equals(that.error) : that.error != null) {
            return false;
        }
        if (details != null ? !details.equals(that.details) : that.details != null) {
            return false;
        }
        return timeStamp != null ? timeStamp.equals(that.timeStamp) : that.timeStamp == null;
    }

    @Override
    public int hashCode() {
        int result = error != null ? error.hashCode() : 0;
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        return result;
    }
}
