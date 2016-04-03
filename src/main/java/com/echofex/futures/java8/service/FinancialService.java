package com.echofex.futures.java8.service;

import com.echofex.model.BankDetails;

import java.util.concurrent.CompletableFuture;

/**
 * Created by robin on 3/6/16.
 */
public interface FinancialService {

    CompletableFuture<String> getCurrencyCodeForCountry(CompletableFuture<String> countryCode);

    CompletableFuture<Double> getCurrencyConversion(CompletableFuture<String> fromCurrencyCode, CompletableFuture<String> toCountryCode);

    CompletableFuture<BankDetails> getBankDetailsForUser(CompletableFuture<Long> userId);



}
