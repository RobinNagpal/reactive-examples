package com.echofex.futures.java5concurrent;


import com.echofex.futures.java5concurrent.service.*;
import com.echofex.futures.java5concurrent.service.impl.EmploymentServiceImpl;
import com.echofex.futures.java5concurrent.service.impl.ExecutorServiceImpl;
import com.echofex.futures.java5concurrent.service.impl.FinancialServiceImpl;
import com.echofex.futures.java5concurrent.service.impl.UserServiceImpl;
import com.echofex.futures.java5concurrent.util.MoneyTransferServiceFactory;
import com.echofex.model.BankDetails;
import com.echofex.model.Employer;
import com.echofex.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
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

        List<Future<Double>> employerEarningsList = new ArrayList<Future<Double>>(employers.get().size());
        for (Employer employer : employers.get()) {
            Future<Double> earlyEarningsInHomeCountry = getEarningFromEmployer(userId, homeCurrencyOfUser, employer);
            employerEarningsList.add(earlyEarningsInHomeCountry);
        }

        for (Future<Double> employerEarning : employerEarningsList) {
            totalEarnings += employerEarning.get();
        }

        Future<BankDetails> bankDetails = financialService.getBankDetailsForUser(new DummyFuture<Long>(userId));

        MoneyTransferService moneyTransferService = MoneyTransferServiceFactory.getMoneyTransferServiceForCurrency(homeCurrencyOfUser);

        moneyTransferService.transferMoneyToAccount(new DummyFuture<String>(bankDetails.get().getBankName()), new DummyFuture<String>(bankDetails.get().getAccountNumber()), new DummyFuture<Double>(totalEarnings) );


    }

    private Future<Double> getEarningFromEmployer(Long userId, Future<String> homeCurrencyOfUser, Employer employer) {
        final Future<String> employerCurrency = financialService.getCurrencyCodeForCountry(new DummyFuture<String>(employer.getCountryCode()));
        final Future<Double> currencyConversion = financialService.getCurrencyConversion(employerCurrency, homeCurrencyOfUser);
        final Future<Double> yearlyEarnings = employmentService.getYearlyEarningForUserWithEmployer(new DummyFuture<Long>(userId), new DummyFuture<Long>(employer.getId()));

        Callable<Double> worker = new Callable<Double>(){

            public Double call() throws Exception {
                return currencyConversion.get() * yearlyEarnings.get();
            }
        };

        return ExecutorServiceImpl.executor.submit(worker);
    }

}
