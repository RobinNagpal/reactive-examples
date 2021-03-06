package com.echofex.futures.java5concurrent.service.impl;

import com.echofex.futures.java5concurrent.DummyFuture;
import com.echofex.futures.java5concurrent.service.UserService;
import com.echofex.model.User;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by robin on 3/7/16.
 */
public class UserServiceImpl implements UserService {



    public Future<User> getUserForId(Future<Long> id) throws ExecutionException, InterruptedException {
        id.get();

        return new  DummyFuture<User> (new User());
    }
}
