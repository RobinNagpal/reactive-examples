package com.echofex.futures.java5concurrent.service.impl;

import com.echofex.futures.java5concurrent.service.FinancialService;
import com.echofex.model.BankDetails;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by robin on 3/7/16.
 */
public class FinancialServiceImpl implements FinancialService {


    public Future<String> getCurrencyCodeForCountry(final Future<String> countryCode) {

        Callable<String> worker = new Callable<String>() {

            public String call() throws Exception {
                String country = countryCode.get();
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (country.equals("IN"))
                    return "INR";
                if (country.equals("US"))
                    return "USD";
                if (country.equals("CA"))
                    return "CAD";
                throw new IllegalArgumentException();
            }
        };

        Future<String> future = ExecutorServiceImpl.executor.submit(worker);
        return future;
    }

    public Future<Double> getCurrencyConversion(final Future<String> fromCurrencyCode, final Future<String> toCurrencyCode) {

        Callable<Double> worker = new Callable<Double>() {

            public Double call() throws Exception {

                String fromCurrency = fromCurrencyCode.get();
                String toCurrency = toCurrencyCode.get();

                try {
                    Thread.sleep(2000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (fromCurrency.equals("CAD") && toCurrency.equals("INR"))
                    return 45.00;
                if (fromCurrency.equals("CAD") && toCurrency.equals("USD"))
                    return .730;
                if (fromCurrency.equals("USD") && toCurrency.equals("INR"))
                    return 62.00;
                if (fromCurrency.equals("USD") && toCurrency.equals("CAD"))
                    return 1.35;
                if (fromCurrency.equals("INR") && toCurrency.equals("CAD"))
                    return .022222;
                if (fromCurrency.equals("INR") && toCurrency.equals("USD"))
                    return .017222;
                if (fromCurrency.equals("USD") && toCurrency.equals("USD"))
                    return 1.0;
                if (fromCurrency.equals("INR") && toCurrency.equals("INR"))
                    return 1.0;
                if (fromCurrency.equals("CAD") && toCurrency.equals("CAD"))
                    return 1.0;

                throw new IllegalArgumentException("Cannot find conversion from " + fromCurrencyCode + "  to  " + toCurrency);
            }
        };

        Future<Double> future = ExecutorServiceImpl.executor.submit(worker);
        return future;
    }

    public Future<BankDetails> getBankDetailsForUser(final Future<Long> userIdF) {

        Callable<BankDetails> worker = new Callable<BankDetails>() {

            public BankDetails call() throws Exception {

                Long userId = userIdF.get();

                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                return new BankDetails("4056777", "Royal Bank Of Canada");

            }
        };


        Future<BankDetails> future = ExecutorServiceImpl.executor.submit(worker);
        return future;
    }
}
