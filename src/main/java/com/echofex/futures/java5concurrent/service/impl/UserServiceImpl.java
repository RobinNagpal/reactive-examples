package com.echofex.futures.java5concurrent.service.impl;

import com.echofex.futures.java5concurrent.service.ExecutorService;
import com.echofex.futures.java5concurrent.service.UserService;
import com.echofex.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Future;

/**
 * Created by robin on 3/7/16.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private ExecutorService executorService;

    public Future<User> getUserForId(Future<Long> id) {


        return new User();
    }
}
