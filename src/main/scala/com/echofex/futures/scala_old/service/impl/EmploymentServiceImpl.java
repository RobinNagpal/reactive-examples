package com.echofex.futures.scala_old.service.impl;

import com.echofex.futures.scala_old.service.EmploymentService;
import com.echofex.httpservices.HttpEmploymentService;
import com.echofex.httpservices.impl.HttpEmploymentServiceImpl;
import com.echofex.model.Employer;

import java.util.List;

/**
 * Created by robin on 3/7/16.
 */
public class EmploymentServiceImpl implements EmploymentService {
    HttpEmploymentService httpEmploymentService = new HttpEmploymentServiceImpl();

    public List<Employer> findEmployersForUserInYear(long userId, String year) {
        return httpEmploymentService.findEmployersForUserInYear(userId, year);
    }

    public Double getYearlyEarningForUserWithEmployer(long userId, long employerId) {
        return httpEmploymentService.getYearlyEarningForUserWithEmployer(userId, employerId);
    }
}
