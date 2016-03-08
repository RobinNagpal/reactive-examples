package com.echofex.futures.java5.service.impl;

import com.echofex.futures.java5.service.MoneyTransferService;

/**
 * Created by robin on 3/7/16.
 */
public class MoneyTransferServiceImpl implements MoneyTransferService {

    public void transferMoneyToAccount(String bankName, String accountNumber, Double amount) {

        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
