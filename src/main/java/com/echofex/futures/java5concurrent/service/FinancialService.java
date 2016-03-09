package com.echofex.futures.java5concurrent.service;

import com.echofex.model.BankDetails;

import java.util.concurrent.Future;

/**
 * Created by robin on 3/6/16.
 */
public interface FinancialService {

    Future<String> getCurrencyCodeForCountry(Future<String> countryCode);

    Future<Double> getCurrencyConversion(Future<String> fromCurrencyCode, Future<String> toCountryCode);

    Future<BankDetails> getBankDetailsForUser(Future<Long> userId);



}
