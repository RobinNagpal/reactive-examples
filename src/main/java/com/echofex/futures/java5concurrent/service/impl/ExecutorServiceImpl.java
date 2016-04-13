package com.echofex.futures.java5concurrent.service.impl;

import java.util.concurrent.Executors;

/**
 * Created by rnagpal2 on 3/9/2016.
 */
public class ExecutorServiceImpl{
    private static final int NTHREDS = 20;
    public static java.util.concurrent.ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);

}
