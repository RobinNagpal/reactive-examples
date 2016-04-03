package com.echofex.futures.java8.util;

import com.echofex.futures.java8.service.MoneyTransferService;
import com.echofex.futures.java8.service.impl.MoneyTransferServiceImpl;

import java.util.concurrent.CompletableFuture;

/**
 * Created by robin on 3/6/16.
 */
public class MoneyTransferServiceFactory {

    public static CompletableFuture<MoneyTransferService> getMoneyTransferServiceForCurrency(CompletableFuture<String> currencyCode) {

        return CompletableFuture.completedFuture(new MoneyTransferServiceImpl());
    }


}
