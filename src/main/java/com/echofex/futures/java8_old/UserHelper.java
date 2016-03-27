package com.echofex.futures.java8_old;


import com.echofex.futures.java8_old.service.EmploymentService;
import com.echofex.futures.java8_old.service.FinancialService;
import com.echofex.futures.java8_old.service.MoneyTransferService;
import com.echofex.futures.java8_old.service.UserService;
import com.echofex.futures.java8_old.service.impl.EmploymentServiceImpl;
import com.echofex.futures.java8_old.service.impl.FinancialServiceImpl;
import com.echofex.futures.java8_old.service.impl.UserServiceImpl;
import com.echofex.futures.java8_old.util.MoneyTransferServiceFactory;
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


        CompletableFuture<Long> userIdFuture = CompletableFuture.completedFuture(userId);
        CompletableFuture<User> userCF = userIdFuture.thenApplyAsync(userService::getUserForId);
        CompletableFuture<String> userHomeCurrencyCF = userCF.thenApplyAsync(user -> financialService.getCurrencyCodeForCountry(user.getCountryCode()));

        CompletableFuture<List<Employer>> employersListCF = userIdFuture.thenApplyAsync(id -> employmentService.findEmployersForUserInYear(id, "2015"));


        CompletableFuture<Stream<Double>> employerSalaries = employersListCF.thenApplyAsync(employers -> {
                    return employers.stream().parallel().map(emp -> {
                                String userHomeCurrency = null;
                                try {
                                    userHomeCurrency = userHomeCurrencyCF.get();
                                } catch (Exception e) {
                                    e.printStackTrace();

                                }


                                String employerCurrency = financialService.getCurrencyCodeForCountry(emp.getCountryCode());
                                Double currencyConversion = financialService.getCurrencyConversion(employerCurrency, userHomeCurrency);
                                Double yearlyEarnings = employmentService.getYearlyEarningForUserWithEmployer(userId, emp.getId());
                                Double earlyEarningsInHomeCountry = currencyConversion * yearlyEarnings;
                                System.out.println("Yearly Earning " + earlyEarningsInHomeCountry);
                                return earlyEarningsInHomeCountry;


                            }
                    );
                }
        );


        List<Double> salaries = employerSalaries.get().collect(Collectors.toList());
        double totalSalary = 0.0;
        for (Double salary : salaries) {

            totalSalary += salary;
        }

        CompletableFuture<BankDetails> bankDetailsCF = userIdFuture.thenApplyAsync(financialService::getBankDetailsForUser);
        CompletableFuture<MoneyTransferService> moneyTransferServiceCF = userHomeCurrencyCF.thenApplyAsync(MoneyTransferServiceFactory::getMoneyTransferServiceForCurrency);


        MoneyTransferService moneyTransferService = moneyTransferServiceCF.get();
        BankDetails bankDetails = bankDetailsCF.get();
        return moneyTransferService.transferMoneyToAccount(bankDetails.getBankName(), bankDetails.getAccountNumber(), totalSalary);
    }


}
