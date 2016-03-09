package com.echofex.futures.java5concurrent.service.impl;

import com.echofex.futures.java5concurrent.service.ExecutorService;
import com.echofex.futures.java5concurrent.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by robin on 3/7/16.
 */
public class MoneyTransferServiceImpl implements MoneyTransferService {
    @Autowired
    private ExecutorService executorService;

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
        executorService.submitTask(worker);
    }



}
