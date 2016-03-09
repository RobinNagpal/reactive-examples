package com.echofex.futures.java5;


import com.echofex.futures.java5.service.EmploymentService;
import com.echofex.futures.java5.service.FinancialService;
import com.echofex.futures.java5.service.MoneyTransferService;
import com.echofex.futures.java5.service.UserService;
import com.echofex.futures.java5.service.impl.EmploymentServiceImpl;
import com.echofex.futures.java5.service.impl.FinancialServiceImpl;
import com.echofex.futures.java5.service.impl.UserServiceImpl;
import com.echofex.futures.util.MoneyTransferServiceFactory;
import com.echofex.model.BankDetails;
import com.echofex.model.Employer;
import com.echofex.model.User;

import java.util.List;

/**
 * Created by robin on 3/6/16.
 */
public class UserHelper {


    UserService userService = new UserServiceImpl();
    EmploymentService employmentService = new EmploymentServiceImpl();
    FinancialService financialService = new FinancialServiceImpl();

    public void transferYearlyEarningsToUsersAccount(long userId){
        User user = userService.getUserForId(userId);

        List<Employer> employers = employmentService.findEmployersForUserInYear(userId, "2015");

        String homeCurrencyOfUser = financialService.getCurrencyCodeForCountry(user.getCountryCode());


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
