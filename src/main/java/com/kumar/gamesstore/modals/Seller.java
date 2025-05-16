package com.kumar.gamesstore.modals;

import com.kumar.gamesstore.domain.AccountStatus;
import com.kumar.gamesstore.domain.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sellerName;

    private long mobile;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Embedded
    private BusinessDetails businessDetails = new BusinessDetails();

    @Embedded
    private BankDetails bankDetails = new BankDetails();

    @OneToOne(cascade = CascadeType.ALL)
    private Address pickupAddress = new Address();

    private String GSTIN;

    private UserRole role = UserRole.ROLE_SELLER;

    private boolean isEmailVerified = false;

    private boolean isPhoneVerified = false;

    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;

    // No-arg constructor
    public Seller() {
    }

    // All-arg constructor
    public Seller(Long id, String sellerName, long mobile, String email, String password, BusinessDetails businessDetails,
            BankDetails bankDetails, Address pickupAddress, String GSTIN, UserRole role, boolean isEmailVerified,
            boolean isPhoneVerified, AccountStatus accountStatus) {
        this.id = id;
        this.sellerName = sellerName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.businessDetails = businessDetails;
        this.bankDetails = bankDetails;
        this.pickupAddress = pickupAddress;
        this.GSTIN = GSTIN;
        this.role = role;
        this.isEmailVerified = isEmailVerified;
        this.isPhoneVerified = isPhoneVerified;
        this.accountStatus = accountStatus;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BusinessDetails getBusinessDetails() {
        return businessDetails;
    }

    public void setBusinessDetails(BusinessDetails businessDetails) {
        this.businessDetails = businessDetails;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

    public Address getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(Address pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public boolean isPhoneVerified() {
        return isPhoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        isPhoneVerified = phoneVerified;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Seller)) {
            return false;
        }

        Seller seller = (Seller) o;

        if (mobile != seller.mobile) {
            return false;
        }
        if (id != null ? !id.equals(seller.id) : seller.id != null) {
            return false;
        }
        if (sellerName != null ? !sellerName.equals(seller.sellerName) : seller.sellerName != null) {
            return false;
        }
        if (email != null ? !email.equals(seller.email) : seller.email != null) {
            return false;
        }
        if (password != null ? !password.equals(seller.password) : seller.password != null) {
            return false;
        }
        if (businessDetails != null ? !businessDetails.equals(seller.businessDetails) : seller.businessDetails != null) {
            return false;
        }
        if (bankDetails != null ? !bankDetails.equals(seller.bankDetails) : seller.bankDetails != null) {
            return false;
        }
        if (pickupAddress != null ? !pickupAddress.equals(seller.pickupAddress) : seller.pickupAddress != null) {
            return false;
        }
        if (GSTIN != null ? !GSTIN.equals(seller.GSTIN) : seller.GSTIN != null) {
            return false;
        }
        if (role != seller.role) {
            return false;
        }
        if (isEmailVerified != seller.isEmailVerified) {
            return false;
        }
        if (isPhoneVerified != seller.isPhoneVerified) {
            return false;
        }
        return accountStatus == seller.accountStatus;
    }

    // hashCode
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sellerName != null ? sellerName.hashCode() : 0);
        result = 31 * result + (int) (mobile ^ (mobile >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (businessDetails != null ? businessDetails.hashCode() : 0);
        result = 31 * result + (bankDetails != null ? bankDetails.hashCode() : 0);
        result = 31 * result + (pickupAddress != null ? pickupAddress.hashCode() : 0);
        result = 31 * result + (GSTIN != null ? GSTIN.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (isEmailVerified ? 1 : 0);
        result = 31 * result + (isPhoneVerified ? 1 : 0);
        result = 31 * result + (accountStatus != null ? accountStatus.hashCode() : 0);
        return result;
    }

    // toString
    @Override
    public String toString() {
        return "Seller{"
                + "id=" + id
                + ", sellerName='" + sellerName + '\''
                + ", mobile=" + mobile
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", businessDetails=" + businessDetails
                + ", bankDetails=" + bankDetails
                + ", pickupAddress=" + pickupAddress
                + ", GSTIN='" + GSTIN + '\''
                + ", role=" + role
                + ", isEmailVerified=" + isEmailVerified
                + ", isPhoneVerified=" + isPhoneVerified
                + ", accountStatus=" + accountStatus
                + '}';
    }
}
