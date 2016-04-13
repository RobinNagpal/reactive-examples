package com.echofex.futures.scala_old;

import com.echofex.futures.scala_old.UserHelper;
// import com.echofex.futures.java8_old.UserHelperWithCustomPool;
import org.junit.Test;

import java.util.Date;

/**
 * Created by rnagpal2 on 3/13/2016.
 */
public class UserHelperTest {


    @Test
    public void testTransferYearlyEarningsToUsersAccount() throws Exception {
        UserHelper userHelper = new UserHelper();

        long startTime = new Date().getTime();
        Double result = userHelper.transferYearlyEarningsToUsersAccount(333l);
        long endTime = new Date().getTime();
        System.out.println("Result :" + result);
        System.out.println("Time taken :" + (endTime - startTime));
    }


/*
    @Test
    public void testTransferYearlyEarningsToUsersAccountWithCustomPool() throws Exception {
        UserHelperWithCustomPool userHelper = new UserHelperWithCustomPool();

        long startTime = new Date().getTime();
        Double result = userHelper.transferYearlyEarningsToUsersAccount(333l);
        long endTime = new Date().getTime();
        System.out.println("Result :" + result);
        System.out.println("Time taken :" + (endTime - startTime));
    }
*/
}