package com.echofex.futures.java5concurrent.service;

import com.echofex.model.User;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by robin on 3/6/16.
 */
public interface UserService {

    Future<User> getUserForId(Future<Long> id) throws ExecutionException, InterruptedException;


}
