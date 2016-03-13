package com.echofex.futures.java8_old;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by rnagpal2 on 3/13/2016.
 */
public class UserHelperTest {

    UserHelper userHelper = new UserHelper();

    @Test
    public void testTransferYearlyEarningsToUsersAccount() throws Exception {
        long startTime = new Date().getTime();
        userHelper.transferYearlyEarningsToUsersAccount(333l);
        long endTime = new Date().getTime();

        System.out.println("Time taken :" + (endTime - startTime));
    }
}