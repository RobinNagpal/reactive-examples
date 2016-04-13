package com.echofex.futures.scala_old.service.impl;

import com.echofex.futures.scala_old.service.MoneyTransferService;
import com.echofex.httpservices.HttpMoneyTransferService;
import com.echofex.httpservices.impl.HttpMoneyTransferServiceImpl;

/**
 * Created by robin on 3/7/16.
 */
public class MoneyTransferServiceImpl implements MoneyTransferService {

    HttpMoneyTransferService httpMoneyTransferService = new HttpMoneyTransferServiceImpl();

    public Double transferMoneyToAccount(String bankName, String accountNumber, Double amount) {
        return httpMoneyTransferService.transferMoneyToAccount(bankName, accountNumber, amount);
    }

}
