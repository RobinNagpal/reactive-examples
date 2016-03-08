package com.echofex.futures.java5.service.impl;

import com.echofex.futures.java5.service.FinancialService;
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

        return null;
    }

    public Double getCurrencyConversion(String fromCurrencyCode, String toCountryCode) {
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (fromCurrencyCode.equals("CAD") && toCountryCode.equals("INR"))
            return 45.00;
        if (fromCurrencyCode.equals("CAD") && toCountryCode.equals("USD"))
            return .730;
        if (fromCurrencyCode.equals("USD") && toCountryCode.equals("INR"))
            return 62.00;
        if (fromCurrencyCode.equals("USD") && toCountryCode.equals("CAD"))
            return 1.35;
        if (fromCurrencyCode.equals("INR") && toCountryCode.equals("CAD"))
            return .022222;
        if (fromCurrencyCode.equals("INR") && toCountryCode.equals("USD"))
            return .017222;

        return null;
    }

    public BankDetails getBankDetailsForUser(long userId) {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
