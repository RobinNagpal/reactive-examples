package com.echofex.futures.java5;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by robin on 3/7/16.
 */
public class UserHelperTest {

    UserHelper userHelper = new UserHelper();

    @org.junit.Test
    public void testTransferYearlyEarningsToUsersAccount() throws Exception {
        long startTime = new Date().getTime();
        userHelper.transferYearlyEarningsToUsersAccount(333l);
        long endTime = new Date().getTime();

        System.out.println("Time taken :" + (endTime - startTime));

    }
}