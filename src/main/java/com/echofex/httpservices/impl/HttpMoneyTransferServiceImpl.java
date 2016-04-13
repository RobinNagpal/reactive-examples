package com.echofex.httpservices.impl;

import com.echofex.httpservices.HttpMoneyTransferService;

/**
 * Created by robin on 3/7/16.
 */
public class HttpMoneyTransferServiceImpl implements HttpMoneyTransferService {

    public Double transferMoneyToAccount(String bankName, String accountNumber, Double amount) {

        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return amount;

    }


}
