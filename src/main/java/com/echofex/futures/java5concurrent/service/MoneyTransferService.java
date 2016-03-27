package com.echofex.futures.java5concurrent.service;

import java.util.concurrent.Future;

/**
 * Created by robin on 3/6/16.
 */
public interface MoneyTransferService {


    Future<Double> transferMoneyToAccount(Future<String> bankName, Future<String> accountNumber, Future<Double> amount);
}
