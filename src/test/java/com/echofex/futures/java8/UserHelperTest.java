package com.echofex.futures.java8;


import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by rnagpal2 on 4/3/2016.
 */
public class UserHelperTest {

    UserHelper userHelper = new UserHelper();

    @Test
    public void testTransferYearlyEarningsToUsersAccount() throws Exception {
        long startTime = new Date().getTime();
        Double result = userHelper.transferYearlyEarningsToUsersAccount(333l);
        long endTime = new Date().getTime();
        System.out.println("Result :" + result);
        System.out.println("Time taken :" + (endTime - startTime));
    }
}