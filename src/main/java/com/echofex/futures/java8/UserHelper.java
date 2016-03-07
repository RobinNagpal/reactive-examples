package com.echofex.futures.java8;


import com.echofex.futures.java8.service.UserService;

/**
 * Created by robin on 3/6/16.
 */
public class UserHelper {

    UserService userService = null;

    public void transferYearlyEarningsToUsersAccount(long userId){
        userService.getUserForId(userId);


    }

}
