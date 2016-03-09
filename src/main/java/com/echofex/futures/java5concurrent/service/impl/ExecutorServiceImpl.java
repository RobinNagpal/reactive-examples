package com.echofex.futures.java5concurrent.service.impl;

import com.echofex.futures.java5concurrent.service.ExecutorService;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by rnagpal2 on 3/9/2016.
 */
public class ExecutorServiceImpl implements ExecutorService {
    private static final int NTHREDS = 10;
   private static java.util.concurrent.ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);

    public <T> Future<T> submitTask(Callable<T> task) {
        return executor.submit(task);
    }

    public void submitTask(Runnable worker) {
        executor.submit(worker);
    }
}
