package com.echofex.futures.java8.service;

import com.echofex.model.BankDetails;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by robin on 3/6/16.
 */
public interface FinancialService {

    CompletableFuture<String> getCurrencyCodeForCountry(Future<String> countryCode);

    CompletableFuture<Double> getCurrencyConversion(Future<String> fromCurrencyCode, Future<String> toCountryCode);

    CompletableFuture<BankDetails> getBankDetailsForUser(Future<Long> userId);



}
