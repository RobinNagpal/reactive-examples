package com.echofex.futures.scala_old.service.impl;

import com.echofex.futures.scala_old.service.FinancialService;
import com.echofex.httpservices.HttpFinancialService;
import com.echofex.httpservices.impl.HttpFinancialServiceImpl;
import com.echofex.model.BankDetails;

/**
 * Created by robin on 3/7/16.
 */
public class FinancialServiceImpl implements FinancialService {

    HttpFinancialService httpFinancialService = new HttpFinancialServiceImpl();

    public String getCurrencyCodeForCountry(String countryCode) {
        return httpFinancialService.getCurrencyCodeForCountry(countryCode);
    }

    public Double getCurrencyConversion(String fromCurrencyCode, String toCurrencyCode) {
        return httpFinancialService.getCurrencyConversion(fromCurrencyCode, toCurrencyCode);
    }

    public BankDetails getBankDetailsForUser(long userId) {
        return httpFinancialService.getBankDetailsForUser(userId);
    }
}
