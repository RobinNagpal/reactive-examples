package com.echofex.futures.java8.service;

/**
 * Created by robin on 3/6/16.
 */
public interface MoneyTransferService {


    Double transferMoneyToAccount(String bankName, String accountNumber, Double amount);
}
