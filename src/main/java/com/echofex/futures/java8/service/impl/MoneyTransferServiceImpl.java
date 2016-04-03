package com.echofex.futures.java8.service.impl;

import com.echofex.futures.java8.service.MoneyTransferService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by robin on 3/7/16.
 */
public class MoneyTransferServiceImpl implements MoneyTransferService {

    public CompletableFuture<Double> transferMoneyToAccount(CompletableFuture<String> bankNameCF, CompletableFuture<String> accountNumberCF, CompletableFuture<Double> amountCF) {

        CompletableFuture<Void> voidCF = CompletableFuture.allOf(bankNameCF, accountNumberCF, amountCF);
        CompletableFuture<Double> moneyTransferred = voidCF.thenApplyAsync((Void) -> {
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Double amount = 0.0;
            try {
                amount = amountCF.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return amount;

        });

        return moneyTransferred;
    }


}
