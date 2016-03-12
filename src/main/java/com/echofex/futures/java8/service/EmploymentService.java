package com.echofex.futures.java8.service;

import com.echofex.model.Employer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by robin on 3/6/16.
 */
public interface EmploymentService {

    CompletableFuture<List<Employer>> findEmployersForUserInYear(CompletableFuture<Long> userId, CompletableFuture<String> year);

    CompletableFuture<Double> getYearlyEarningForUserWithEmployer(CompletableFuture<Long> userId, CompletableFuture<Long> employerId);
}
