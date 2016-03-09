package com.echofex.futures.java5concurrent.service;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by rnagpal2 on 3/9/2016.
 */
public interface ExecutorService {

    public <T> Future<T> submitTask(Callable<T> task);

    void submitTask(Runnable worker);
}
