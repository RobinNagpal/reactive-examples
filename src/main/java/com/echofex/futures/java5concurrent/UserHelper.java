package com.echofex.futures.java5concurrent;


import com.echofex.futures.java5concurrent.service.EmploymentService;
import com.echofex.futures.java5concurrent.service.FinancialService;
import com.echofex.futures.java5concurrent.service.MoneyTransferService;
import com.echofex.futures.java5concurrent.service.UserService;
import com.echofex.futures.java5concurrent.service.impl.EmploymentServiceImpl;
import com.echofex.futures.java5concurrent.service.impl.FinancialServiceImpl;
import com.echofex.futures.java5concurrent.service.impl.UserServiceImpl;
import com.echofex.futures.util.MoneyTransferServiceFactory;
import com.echofex.model.BankDetails;
import com.echofex.model.Employer;
import com.echofex.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by robin on 3/6/16.
 */
public class UserHelper {


    UserService userService = new UserServiceImpl();
    EmploymentService employmentService = new EmploymentServiceImpl();
    FinancialService financialService = new FinancialServiceImpl();

    public void transferYearlyEarningsToUsersAccount(Long userId) throws ExecutionException, InterruptedException {
        Future<User> user = userService.getUserForId(new DummyFuture<Long>(userId));

        Future<List<Employer>> employers = employmentService.findEmployersForUserInYear(new DummyFuture<Long>(userId), new DummyFuture<String>("2015"));

        Future<String> homeCurrencyOfUser = financialService.getCurrencyCodeForCountry(new DummyFuture<String>(user.get().getCountryCode()));


        Double totalEarnings = 0.0;

        for (Employer employer : employers) {
            String countryCode = employer.getCountryCode();
            String employerCurrency = financialService.getCurrencyCodeForCountry(countryCode);
            Double currencyConversion = financialService.getCurrencyConversion(employerCurrency, homeCurrencyOfUser);

            Double yearlyEarnings = employmentService.getYearlyEarningForUserWithEmployer(userId, employer.getId());

            Double earlyEarningsInHomeCountry = currencyConversion * yearlyEarnings;

            totalEarnings += earlyEarningsInHomeCountry;
        }

        BankDetails bankDetails = financialService.getBankDetailsForUser(userId);

        MoneyTransferService moneyTransferService = MoneyTransferServiceFactory.getMoneyTransferServiceForCurrency(homeCurrencyOfUser);

        moneyTransferService.transferMoneyToAccount(bankDetails.getBankName(), bankDetails.getAccountNumber(), totalEarnings );


    }

}
