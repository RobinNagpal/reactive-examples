package com.echofex.futures.java8.service;

import java.util.concurrent.Future;

/**
 * Created by robin on 3/6/16.
 */
public interface MoneyTransferService {


    void transferMoneyToAccount(Future<String> bankName, Future<String> accountNumber, Future<Double> amount);
}