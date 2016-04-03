package com.echofex.futures.java8.service;

import com.echofex.model.User;

import java.util.concurrent.CompletableFuture;

/**
 * Created by robin on 3/6/16.
 */
public interface UserService {

    CompletableFuture<User> getUserForId(CompletableFuture<Long> id);


}
