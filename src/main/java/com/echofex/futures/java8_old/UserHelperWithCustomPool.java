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
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by robin on 3/6/16.
 */
public class UserHelperWithCustomPool {


    UserService userService = new UserServiceImpl();
    EmploymentService employmentService = new EmploymentServiceImpl();
    FinancialService financialService = new FinancialServiceImpl();

    private static final int NTHREDS = 20;
    public static java.util.concurrent.ExecutorService pool = Executors.newFixedThreadPool(NTHREDS);

    public Double transferYearlyEarningsToUsersAccount(Long userId) throws ExecutionException, InterruptedException {


        CompletableFuture<Long> userIdFuture = CompletableFuture.completedFuture(userId);
        CompletableFuture<User> userCF = userIdFuture.thenApplyAsync(userService::getUserForId, pool);
        CompletableFuture<String> userHomeCurrencyCF = userCF.thenApplyAsync(user -> financialService.getCurrencyCodeForCountry(user.getCountryCode()), pool);

        CompletableFuture<List<Employer>> employersListCF = userIdFuture.thenApplyAsync(id -> employmentService.findEmployersForUserInYear(id, "2015"), pool);


        CompletableFuture<Stream<CompletableFuture<Double>>> employerSalariesCF = employersListCF.thenApplyAsync(employers ->
                employers.parallelStream().map(emp -> {

                            CompletableFuture<String> employerCurrencyCF = CompletableFuture.supplyAsync(() -> financialService.getCurrencyCodeForCountry(emp.getCountryCode()), pool);

                            CompletableFuture<Double> currencyConvCF = employerCurrencyCF.thenCombineAsync(userHomeCurrencyCF, (employerCurrency, userHomeCurrency) ->
                                    financialService.getCurrencyConversion(employerCurrency, userHomeCurrency), pool
                            );

                            CompletableFuture<Double> yearlyEarningsCF = CompletableFuture.supplyAsync(() -> employmentService.getYearlyEarningForUserWithEmployer(userId, emp.getId()), pool);
                            CompletableFuture<Double> earlyEarningsInHomeCountryCF = currencyConvCF.thenCombineAsync(yearlyEarningsCF, (currencyConv, yearlyEarnings) -> currencyConv * yearlyEarnings, pool);
                            return earlyEarningsInHomeCountryCF;
                        }
                ), pool
        );


        CompletableFuture<BankDetails> bankDetailsCF = userIdFuture.thenApplyAsync(financialService::getBankDetailsForUser, pool);
        CompletableFuture<MoneyTransferService> moneyTransferServiceCF = userHomeCurrencyCF.thenApplyAsync(MoneyTransferServiceFactory::getMoneyTransferServiceForCurrency, pool);


        MoneyTransferService moneyTransferService = moneyTransferServiceCF.get();
        BankDetails bankDetails = bankDetailsCF.get();

        CompletableFuture<Double> totalSalaryCF = employerSalariesCF.thenApplyAsync(salariesStream ->
                salariesStream.mapToDouble(value -> {
                    try {
                        return value.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }).sum()
        );


        return moneyTransferService.transferMoneyToAccount(bankDetails.getBankName(), bankDetails.getAccountNumber(), totalSalaryCF.get());
    }


}
