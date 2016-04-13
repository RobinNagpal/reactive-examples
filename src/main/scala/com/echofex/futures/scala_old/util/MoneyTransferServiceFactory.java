package com.echofex.futures.scala_old.util;

import com.echofex.futures.java8_old.service.MoneyTransferService;
import com.echofex.futures.java8_old.service.impl.MoneyTransferServiceImpl;

/**
 * Created by robin on 3/6/16.
 */
public class MoneyTransferServiceFactory {

    public static MoneyTransferService getMoneyTransferServiceForCurrency(String currencyCode){

        return new MoneyTransferServiceImpl();
    }



}
