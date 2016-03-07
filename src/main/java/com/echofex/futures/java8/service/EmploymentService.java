package com.echofex.futures.java8.service;

import com.echofex.model.Employer;

import java.util.concurrent.Future;

/**
 * Created by robin on 3/6/16.
 */
public interface EmploymentService {

    Future<Employer> findEmployer(String eId);

}
