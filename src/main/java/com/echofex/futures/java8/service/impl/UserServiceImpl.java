package com.echofex.futures.java8.service.impl;

import com.echofex.futures.java8.service.UserService;
import com.echofex.model.User;

import java.util.concurrent.CompletableFuture;

/**
 * Created by robin on 3/7/16.
 */
public class UserServiceImpl implements UserService {

    public CompletableFuture<User> getUserForId(CompletableFuture<Long> id) {

        return CompletableFuture.completedFuture(new User());
    }
}
