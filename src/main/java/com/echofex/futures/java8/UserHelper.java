package com.echofex.futures.java8;


import com.echofex.futures.java8.service.EmploymentService;
import com.echofex.futures.java8.service.FinancialService;
import com.echofex.futures.java8.service.MoneyTransferService;
import com.echofex.futures.java8.service.UserService;
import com.echofex.futures.java8.service.impl.EmploymentServiceImpl;
import com.echofex.futures.java8.service.impl.FinancialServiceImpl;
import com.echofex.futures.java8.service.impl.UserServiceImpl;
import com.echofex.futures.java8.util.MoneyTransferServiceFactory;
import com.echofex.model.BankDetails;
import com.echofex.model.Employer;
import com.echofex.model.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by robin on 3/6/16.
 */
public class UserHelper {


    UserService userService = new UserServiceImpl();
    EmploymentService employmentService = new EmploymentServiceImpl();
    FinancialService financialService = new FinancialServiceImpl();

    public Double transferYearlyEarningsToUsersAccount(Long userId) throws ExecutionException, InterruptedException {


        CompletableFuture<Long> userIdCF = CompletableFuture.completedFuture(userId);
        CompletableFuture<User> userCF = userService.getUserForId(userIdCF);
        CompletableFuture<String> countryCodeCF = userCF.thenApplyAsync(user -> user.getCountryCode());
        CompletableFuture<String> userHomeCurrencyCF = financialService.getCurrencyCodeForCountry(countryCodeCF);
        CompletableFuture<List<Employer>> employersListCF = employmentService.findEmployersForUserInYear(userIdCF, CompletableFuture.completedFuture("2015"));


        CompletableFuture<Stream<CompletableFuture<Double>>> employerSalariesCF = employersListCF.thenApplyAsync(employers ->
                employers.stream().parallel().map(emp ->
                        getYearlyEarningsInHomeCurrencyForEmployer(userIdCF, userHomeCurrencyCF, emp))
        );

        CompletableFuture<Double> totalSalaryCF = employerSalariesCF.thenApplyAsync(salariesStream -> {
            List<CompletableFuture<Double>> salariesCF = salariesStream.collect(Collectors.toList());
            double totalSalary = 0.0;
            for (CompletableFuture<Double> salaryCF : salariesCF) {
                try {
                    totalSalary += salaryCF.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return totalSalary;
        });


        CompletableFuture<BankDetails> bankDetailsCF = financialService.getBankDetailsForUser(userIdCF);
        CompletableFuture<MoneyTransferService> moneyTransferServiceCF = MoneyTransferServiceFactory.getMoneyTransferServiceForCurrency(userHomeCurrencyCF);

        CompletableFuture<Double> yearlyEarningsCF = moneyTransferServiceCF.thenComposeAsync(mtService ->
                mtService.transferMoneyToAccount(bankDetailsCF.thenApplyAsync(bankDetails -> bankDetails.getBankName()), bankDetailsCF.thenApplyAsync(bankDetails -> bankDetails.getAccountNumber()), totalSalaryCF)
        );
        return yearlyEarningsCF.get();
    }

    private CompletableFuture<Double> getYearlyEarningsInHomeCurrencyForEmployer(CompletableFuture<Long> userIdCF, CompletableFuture<String> userHomeCurrencyCF, Employer emp) {
        CompletableFuture<String> empCountryCodeCF = CompletableFuture.completedFuture(emp.getCountryCode());
        CompletableFuture<String> employerCurrencyCF = financialService.getCurrencyCodeForCountry(empCountryCodeCF);
        CompletableFuture<Double> currencyConvCF = financialService.getCurrencyConversion(employerCurrencyCF, userHomeCurrencyCF);
        CompletableFuture<Double> yearlyEarningsCF = employmentService.getYearlyEarningForUserWithEmployer(userIdCF, CompletableFuture.completedFuture(emp.getId()));
        CompletableFuture<Double> earlyEarningsInHomeCountryCF = currencyConvCF.thenCombineAsync(yearlyEarningsCF, (currencyConv, yearlyEarnings) -> currencyConv * yearlyEarnings);
        return earlyEarningsInHomeCountryCF;
    }


}
