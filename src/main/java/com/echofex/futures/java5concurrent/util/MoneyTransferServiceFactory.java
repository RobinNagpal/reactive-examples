package com.echofex.futures.java5concurrent.util;

import com.echofex.futures.java5concurrent.service.MoneyTransferService;
import com.echofex.futures.java5concurrent.service.impl.MoneyTransferServiceImpl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by robin on 3/6/16.
 */
public class MoneyTransferServiceFactory {

    public static MoneyTransferService getMoneyTransferServiceForCurrency(Future<String> currencyCode) throws ExecutionException, InterruptedException {

        currencyCode.get();
        return new MoneyTransferServiceImpl();
    }



}
