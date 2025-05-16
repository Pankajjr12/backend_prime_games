package com.kumar.gamesstore.modals;

public class BankDetails {

    private String accountNumber;
    private String accountHolderName;
    private String ifsCode;

    // No-argument constructor
    public BankDetails() {
    }

    // All-argument constructor
    public BankDetails(String accountNumber, String accountHolderName, String ifsCode) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.ifsCode = ifsCode;
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getIfsCode() {
        return ifsCode;
    }

    public void setIfsCode(String ifsCode) {
        this.ifsCode = ifsCode;
    }

    // toString method
    @Override
    public String toString() {
        return "BankDetails{"
                + "accountNumber='" + accountNumber + '\''
                + ", accountHolderName='" + accountHolderName + '\''
                + ", ifsCode='" + ifsCode + '\''
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

        BankDetails that = (BankDetails) o;

        if (accountNumber != null ? !accountNumber.equals(that.accountNumber) : that.accountNumber != null) {
            return false;
        }
        if (accountHolderName != null ? !accountHolderName.equals(that.accountHolderName) : that.accountHolderName != null) {
            return false;
        }
        return ifsCode != null ? ifsCode.equals(that.ifsCode) : that.ifsCode == null;
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result = accountNumber != null ? accountNumber.hashCode() : 0;
        result = 31 * result + (accountHolderName != null ? accountHolderName.hashCode() : 0);
        result = 31 * result + (ifsCode != null ? ifsCode.hashCode() : 0);
        return result;
    }
}
