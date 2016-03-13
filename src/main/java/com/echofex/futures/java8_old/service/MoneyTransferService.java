package com.echofex.futures.java8_old.service;

/**
 * Created by robin on 3/6/16.
 */
public interface MoneyTransferService {


    void transferMoneyToAccount(String bankName, String accountNumber, Double amount);
}
