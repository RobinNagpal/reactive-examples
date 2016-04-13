package com.echofex.httpservices;

import com.echofex.model.BankDetails;

/**
 * Created by robin on 3/6/16.
 */
public interface HttpFinancialService {

    String getCurrencyCodeForCountry(String countryCode);

    Double getCurrencyConversion(String fromCurrencyCode, String toCountryCode);

    BankDetails getBankDetailsForUser(long userId);



}
