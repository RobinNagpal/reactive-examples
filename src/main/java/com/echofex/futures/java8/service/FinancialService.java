package com.echofex.futures.java8.service;

import com.echofex.model.BankDetails;

import java.util.concurrent.Future;

/**
 * Created by robin on 3/6/16.
 */
public interface FinancialService {

    Future<String> getCurrencyCodeForCountry(String countryCode);

    Future<Double> getCurrencyConversion(String fromCurrencyCode, String toCountryCode);

    Future<BankDetails> getBankDetailsForUser(long userId);

    void transferMoneyToAccount(String bankName, String accountNumber, Double amount);

}
