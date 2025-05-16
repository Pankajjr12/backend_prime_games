package com.kumar.gamesstore.modals;

import com.kumar.gamesstore.domain.PaymentStatus;

public class PaymentDetails {

    private String paymentId;
    private String razorPaymentLinkId;
    private String razorPaymentLinkReferenceId;
    private String razorPaymentLinkStatus;
    private String razorPaymentId;

    private PaymentStatus status;

    // No-arg constructor
    public PaymentDetails() {
    }

    // All-arg constructor
    public PaymentDetails(String paymentId, String razorPaymentLinkId, String razorPaymentLinkReferenceId,
            String razorPaymentLinkStatus, String razorPaymentId, PaymentStatus status) {
        this.paymentId = paymentId;
        this.razorPaymentLinkId = razorPaymentLinkId;
        this.razorPaymentLinkReferenceId = razorPaymentLinkReferenceId;
        this.razorPaymentLinkStatus = razorPaymentLinkStatus;
        this.razorPaymentId = razorPaymentId;
        this.status = status;
    }

    // Getters and setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRazorPaymentLinkId() {
        return razorPaymentLinkId;
    }

    public void setRazorPaymentLinkId(String razorPaymentLinkId) {
        this.razorPaymentLinkId = razorPaymentLinkId;
    }

    public String getRazorPaymentLinkReferenceId() {
        return razorPaymentLinkReferenceId;
    }

    public void setRazorPaymentLinkReferenceId(String razorPaymentLinkReferenceId) {
        this.razorPaymentLinkReferenceId = razorPaymentLinkReferenceId;
    }

    public String getRazorPaymentLinkStatus() {
        return razorPaymentLinkStatus;
    }

    public void setRazorPaymentLinkStatus(String razorPaymentLinkStatus) {
        this.razorPaymentLinkStatus = razorPaymentLinkStatus;
    }

    public String getRazorPaymentId() {
        return razorPaymentId;
    }

    public void setRazorPaymentId(String razorPaymentId) {
        this.razorPaymentId = razorPaymentId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentDetails)) {
            return false;
        }

        PaymentDetails that = (PaymentDetails) o;

        if (paymentId != null ? !paymentId.equals(that.paymentId) : that.paymentId != null) {
            return false;
        }
        if (razorPaymentLinkId != null ? !razorPaymentLinkId.equals(that.razorPaymentLinkId) : that.razorPaymentLinkId != null) {
            return false;
        }
        if (razorPaymentLinkReferenceId != null ? !razorPaymentLinkReferenceId.equals(that.razorPaymentLinkReferenceId) : that.razorPaymentLinkReferenceId != null) {
            return false;
        }
        if (razorPaymentLinkStatus != null ? !razorPaymentLinkStatus.equals(that.razorPaymentLinkStatus) : that.razorPaymentLinkStatus != null) {
            return false;
        }
        if (razorPaymentId != null ? !razorPaymentId.equals(that.razorPaymentId) : that.razorPaymentId != null) {
            return false;
        }
        return status == that.status;
    }

    // hashCode
    @Override
    public int hashCode() {
        int result = paymentId != null ? paymentId.hashCode() : 0;
        result = 31 * result + (razorPaymentLinkId != null ? razorPaymentLinkId.hashCode() : 0);
        result = 31 * result + (razorPaymentLinkReferenceId != null ? razorPaymentLinkReferenceId.hashCode() : 0);
        result = 31 * result + (razorPaymentLinkStatus != null ? razorPaymentLinkStatus.hashCode() : 0);
        result = 31 * result + (razorPaymentId != null ? razorPaymentId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    // toString
    @Override
    public String toString() {
        return "PaymentDetails{"
                + "paymentId='" + paymentId + '\''
                + ", razorPaymentLinkId='" + razorPaymentLinkId + '\''
                + ", razorPaymentLinkReferenceId='" + razorPaymentLinkReferenceId + '\''
                + ", razorPaymentLinkStatus='" + razorPaymentLinkStatus + '\''
                + ", razorPaymentId='" + razorPaymentId + '\''
                + ", status=" + status
                + '}';
    }
}
