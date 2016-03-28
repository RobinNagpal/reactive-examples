package com.echofex.futures.java8.service.impl;

import com.echofex.futures.java8.service.FinancialService;
import com.echofex.model.BankDetails;

/**
 * Created by robin on 3/7/16.
 */
public class FinancialServiceImpl implements FinancialService {
    public String getCurrencyCodeForCountry(String countryCode) {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(countryCode.equals("IN"))
            return "INR";
        if(countryCode.equals("US"))
            return "USD";
        if(countryCode.equals("CA"))
            return "CAD";

        throw new IllegalArgumentException();
    }

    public Double getCurrencyConversion(String fromCurrencyCode, String toCurrencyCode) {
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (fromCurrencyCode.equals("CAD") && toCurrencyCode.equals("INR"))
            return 45.00;
        if (fromCurrencyCode.equals("CAD") && toCurrencyCode.equals("USD"))
            return .730;
        if (fromCurrencyCode.equals("USD") && toCurrencyCode.equals("INR"))
            return 62.00;
        if (fromCurrencyCode.equals("USD") && toCurrencyCode.equals("CAD"))
            return 1.35;
        if (fromCurrencyCode.equals("INR") && toCurrencyCode.equals("CAD"))
            return .022222;
        if (fromCurrencyCode.equals("INR") && toCurrencyCode.equals("USD"))
            return .017222;
        if (fromCurrencyCode.equals("USD") && toCurrencyCode.equals("USD"))
            return 1.0;
        if (fromCurrencyCode.equals("INR") && toCurrencyCode.equals("INR"))
            return 1.0;
        if (fromCurrencyCode.equals("CAD") && toCurrencyCode.equals("CAD"))
            return 1.0;

        throw new IllegalArgumentException("Cannot find conversion from " + fromCurrencyCode + "  to  " + toCurrencyCode );
    }

    public BankDetails getBankDetailsForUser(long userId) {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return new BankDetails("4056777", "Royal Bank Of Canada");
    }
}
