package com.echofex.httpservices;

/**
 * Created by robin on 3/6/16.
 */
public interface HttpMoneyTransferService {


    Double transferMoneyToAccount(String bankName, String accountNumber, Double amount);
}
