package com.echofex.futures.java5concurrent.service;

import com.echofex.model.Employer;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by robin on 3/6/16.
 */
public interface EmploymentService {

    Future<List<Employer>> findEmployersForUserInYear(Future<Long> userId, Future<String> year);

    Future<Double> getYearlyEarningForUserWithEmployer(Future<Long> userId, Future<Long> employerId);
}
