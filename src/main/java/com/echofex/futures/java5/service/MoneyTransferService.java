package com.echofex.futures.java5.service;

/**
 * Created by robin on 3/6/16.
 */
public interface MoneyTransferService {


    void transferMoneyToAccount(String bankName, String accountNumber, Double amount);
}
