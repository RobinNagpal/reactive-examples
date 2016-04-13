package com.echofex.futures.scala_old.service;

import com.echofex.model.BankDetails;

/**
 * Created by robin on 3/6/16.
 */
public interface FinancialService {

    String getCurrencyCodeForCountry(String countryCode);

    Double getCurrencyConversion(String fromCurrencyCode, String toCountryCode);

    BankDetails getBankDetailsForUser(long userId);



}
