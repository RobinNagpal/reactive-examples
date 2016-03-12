package com.echofex.futures.java5concurrent;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by rnagpal2 on 3/12/2016.
 */
public class UserHelperTest {

    private UserHelper userHelper = new UserHelper();

    @Test
    public void testTransferYearlyEarningsToUsersAccount() throws Exception {
        long startTime = new Date().getTime();
        userHelper.transferYearlyEarningsToUsersAccount(333l);
        long endTime = new Date().getTime();

        System.out.println("Time taken :" + (endTime - startTime));
    }
}