package com.echofex.futures.java8;


import com.echofex.futures.java8.service.EmploymentService;
import com.echofex.futures.java8.service.FinancialService;
import com.echofex.futures.java8.service.MoneyTransferService;
import com.echofex.futures.java8.service.UserService;
import com.echofex.futures.java8.service.impl.EmploymentServiceImpl;
import com.echofex.futures.java8.service.impl.ExecutorServiceImpl;
import com.echofex.futures.java8.service.impl.FinancialServiceImpl;
import com.echofex.futures.java8.service.impl.UserServiceImpl;
import com.echofex.futures.java8.util.MoneyTransferServiceFactory;
import com.echofex.model.BankDetails;
import com.echofex.model.Employer;
import com.echofex.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
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
        CompletableFuture.completedFuture()
        CompletableFuture<User> user = userService.getUserForId(CompletableFuture.completedFuture(userId));
        user.thenAccept()
        CompletableFuture<List<Employer>> employers = employmentService.findEmployersForUserInYear(CompletableFuture.completedFuture(userId), CompletableFuture.completedFuture("2015"));

        CompletableFuture<String> homeCurrencyOfUser = financialService.getCurrencyCodeForCountry(CompletableFuture.completedFuture(user.get().getCountryCode()));


        Double totalEarnings = 0.0;

        List<CompletableFuture<Double>> employerEarningsList = new ArrayList<CompletableFuture<Double>>(employers.get().size());
        for (Employer employer : employers.get()) {
            CompletableFuture<Double> earlyEarningsInHomeCountry = getEarningFromEmployer(userId, homeCurrencyOfUser, employer);
            employerEarningsList.add(earlyEarningsInHomeCountry);
        }

        for (CompletableFuture<Double> employerEarning : employerEarningsList) {
            totalEarnings += employerEarning.get();
        }

        CompletableFuture<BankDetails> bankDetails = financialService.getBankDetailsForUser(CompletableFuture.completedFuture(userId));

        MoneyTransferService moneyTransferService = MoneyTransferServiceFactory.getMoneyTransferServiceForCurrency(homeCurrencyOfUser);

        moneyTransferService.transferMoneyToAccount(CompletableFuture.completedFuture(bankDetails.get().getBankName()), CompletableFuture.completedFuture(bankDetails.get().getAccountNumber()), CompletableFuture.completedFuture(totalEarnings));


    }

    private CompletableFuture<Double> getEarningFromEmployer(Long userId, Future<String> homeCurrencyOfUser, Employer employer) {
        final CompletableFuture<String> employerCurrency = financialService.getCurrencyCodeForCountry(CompletableFuture.completedFuture(employer.getCountryCode()));
        final CompletableFuture<Double> currencyConversion = financialService.getCurrencyConversion(employerCurrency, homeCurrencyOfUser);
        final CompletableFuture<Double> yearlyEarnings = employmentService.getYearlyEarningForUserWithEmployer(CompletableFuture.completedFuture(userId), CompletableFuture.completedFuture(employer.getId()));

        Callable<Double> worker = new Callable<Double>() {

            public Double call() throws Exception {
                return currencyConversion.get() * yearlyEarnings.get();
            }
        };

        return ExecutorServiceImpl.executor.submit(worker);
    }

}
