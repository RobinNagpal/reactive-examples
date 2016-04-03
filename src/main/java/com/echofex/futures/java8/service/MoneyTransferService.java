package com.echofex.futures.java8.service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by robin on 3/6/16.
 */
public interface MoneyTransferService {


    CompletableFuture<Double> transferMoneyToAccount(CompletableFuture<String> bankName, CompletableFuture<String> accountNumber, CompletableFuture<Double> amount);
}
