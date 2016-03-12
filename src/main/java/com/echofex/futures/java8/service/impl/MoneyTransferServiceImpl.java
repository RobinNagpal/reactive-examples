package com.echofex.futures.java8.service.impl;

import com.echofex.futures.java8.service.MoneyTransferService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by robin on 3/7/16.
 */
public class MoneyTransferServiceImpl implements MoneyTransferService {
    public void transferMoneyToAccount(final Future<String> bankName, final Future<String> accountNumber, final Future<Double> amount) {
        Runnable worker = new Runnable() {

            public void run() {
                try {
                    bankName.get();
                    accountNumber.get();
                    amount.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        ExecutorServiceImpl.executor.submit(worker);
    }



}
