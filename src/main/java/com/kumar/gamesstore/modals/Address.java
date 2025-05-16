package com.kumar.gamesstore.modals;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String locality;

    private String address;

    private String state;

    private String city;

    private String pinCode;

    private String mobile;

    // No-argument constructor
    public Address() {
    }

    // All-argument constructor
    public Address(Long id, String name, String locality, String address, String state, String city, String pinCode, String mobile) {
        this.id = id;
        this.name = name;
        this.locality = locality;
        this.address = address;
        this.state = state;
        this.city = city;
        this.pinCode = pinCode;
        this.mobile = mobile;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    // toString method
    @Override
    public String toString() {
        return "Address{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", locality='" + locality + '\''
                + ", address='" + address + '\''
                + ", state='" + state + '\''
                + ", city='" + city + '\''
                + ", pinCode='" + pinCode + '\''
                + ", mobile='" + mobile + '\''
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

        Address address1 = (Address) o;

        if (id != null ? !id.equals(address1.id) : address1.id != null) {
            return false;
        }
        if (name != null ? !name.equals(address1.name) : address1.name != null) {
            return false;
        }
        if (locality != null ? !locality.equals(address1.locality) : address1.locality != null) {
            return false;
        }
        if (address != null ? !address.equals(address1.address) : address1.address != null) {
            return false;
        }
        if (state != null ? !state.equals(address1.state) : address1.state != null) {
            return false;
        }
        if (city != null ? !city.equals(address1.city) : address1.city != null) {
            return false;
        }
        if (pinCode != null ? !pinCode.equals(address1.pinCode) : address1.pinCode != null) {
            return false;
        }
        return mobile != null ? mobile.equals(address1.mobile) : address1.mobile == null;
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (pinCode != null ? pinCode.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        return result;
    }
}
