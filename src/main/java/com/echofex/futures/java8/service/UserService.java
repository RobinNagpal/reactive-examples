package com.echofex.futures.java8.service;

import com.echofex.model.User;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by robin on 3/6/16.
 */
public interface UserService {

    CompletableFuture<User> getUserForId(CompletableFuture<Long> id) throws ExecutionException, InterruptedException;


}
