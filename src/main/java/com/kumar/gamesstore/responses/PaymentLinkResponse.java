package com.kumar.gamesstore.responses;

public class PaymentLinkResponse {

    private String payment_link_url;
    private String payment_link_id;

    // No-arg constructor
    public PaymentLinkResponse() {
    }

    // All-args constructor
    public PaymentLinkResponse(String payment_link_url, String payment_link_id) {
        this.payment_link_url = payment_link_url;
        this.payment_link_id = payment_link_id;
    }

    public String getPayment_link_url() {
        return payment_link_url;
    }

    public void setPayment_link_url(String payment_link_url) {
        this.payment_link_url = payment_link_url;
    }

    public String getPayment_link_id() {
        return payment_link_id;
    }

    public void setPayment_link_id(String payment_link_id) {
        this.payment_link_id = payment_link_id;
    }

    @Override
    public String toString() {
        return "PaymentLinkResponse{"
                + "payment_link_url='" + payment_link_url + '\''
                + ", payment_link_id='" + payment_link_id + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentLinkResponse)) {
            return false;
        }

        PaymentLinkResponse that = (PaymentLinkResponse) o;

        if (payment_link_url != null ? !payment_link_url.equals(that.payment_link_url) : that.payment_link_url != null) {
            return false;
        }
        return payment_link_id != null ? payment_link_id.equals(that.payment_link_id) : that.payment_link_id == null;
    }

    @Override
    public int hashCode() {
        int result = payment_link_url != null ? payment_link_url.hashCode() : 0;
        result = 31 * result + (payment_link_id != null ? payment_link_id.hashCode() : 0);
        return result;
    }
}
