package com.echofex.futures.java8.util;

import com.echofex.futures.java8.service.MoneyTransferService;
import com.echofex.futures.java8.service.impl.MoneyTransferServiceImpl;

/**
 * Created by robin on 3/6/16.
 */
public class MoneyTransferServiceFactory {

    public static MoneyTransferService getMoneyTransferServiceForCurrency(String currencyCode){

        return new MoneyTransferServiceImpl();
    }



}
