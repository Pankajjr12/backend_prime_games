package com.kumar.gamesstore.modals;

public class BusinessDetails {

    private String businessName;
    private String businessEmail;
    private String businessAddress;
    private String businessMobile;
    private String logo;
    private String banner;

    // No-argument constructor
    public BusinessDetails() {
    }

    // All-argument constructor
    public BusinessDetails(String businessName, String businessEmail, String businessAddress, String businessMobile, String logo, String banner) {
        this.businessName = businessName;
        this.businessEmail = businessEmail;
        this.businessAddress = businessAddress;
        this.businessMobile = businessMobile;
        this.logo = logo;
        this.banner = banner;
    }

    // Getters and Setters
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessMobile() {
        return businessMobile;
    }

    public void setBusinessMobile(String businessMobile) {
        this.businessMobile = businessMobile;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    // toString method
    @Override
    public String toString() {
        return "BusinessDetails{"
                + "businessName='" + businessName + '\''
                + ", businessEmail='" + businessEmail + '\''
                + ", businessAddress='" + businessAddress + '\''
                + ", businessMobile='" + businessMobile + '\''
                + ", logo='" + logo + '\''
                + ", banner='" + banner + '\''
                + '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BusinessDetails that = (BusinessDetails) o;

        if (businessName != null ? !businessName.equals(that.businessName) : that.businessName != null) {
            return false;
        }
        if (businessEmail != null ? !businessEmail.equals(that.businessEmail) : that.businessEmail != null) {
            return false;
        }
        if (businessAddress != null ? !businessAddress.equals(that.businessAddress) : that.businessAddress != null) {
            return false;
        }
        if (businessMobile != null ? !businessMobile.equals(that.businessMobile) : that.businessMobile != null) {
            return false;
        }
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) {
            return false;
        }
        return banner != null ? banner.equals(that.banner) : that.banner == null;
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result = businessName != null ? businessName.hashCode() : 0;
        result = 31 * result + (businessEmail != null ? businessEmail.hashCode() : 0);
        result = 31 * result + (businessAddress != null ? businessAddress.hashCode() : 0);
        result = 31 * result + (businessMobile != null ? businessMobile.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (banner != null ? banner.hashCode() : 0);
        return result;
    }
}
