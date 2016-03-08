package com.echofex.model;

/**
 * Created by robin on 3/6/16.
 */
public class BankDetails {


    private String accountNumber;
    private String bankName;

    public BankDetails(String accountNumber, String bankName) {
        this.accountNumber = accountNumber;
        this.bankName = bankName;
    }

    public BankDetails() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankName() {
        return bankName;
    }




}
