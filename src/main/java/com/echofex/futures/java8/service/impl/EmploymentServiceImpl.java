package com.echofex.futures.java8.service.impl;

import com.echofex.futures.java8.service.EmploymentService;
import com.echofex.model.Employer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by robin on 3/7/16.
 */
public class EmploymentServiceImpl implements EmploymentService {

    @Override
    public CompletableFuture<List<Employer>> findEmployersForUserInYear(CompletableFuture<Long> userId, CompletableFuture<String> year) {

        Employer metlife = new Employer(12l, "Metlife", "US");
        Employer xebia = new Employer(13l, "Xebia", "IN");
        Employer echofex = new Employer(101l, "Echofex", "CA");

        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Employer> employers = new LinkedList<Employer>();
        employers.add(metlife);
        employers.add(xebia);
        employers.add(echofex);


        return CompletableFuture.completedFuture(employers);
    }

    @Override
    public CompletableFuture<Double> getYearlyEarningForUserWithEmployer(CompletableFuture<Long> userId, CompletableFuture<Long> employerIdCF) {
        try {
            Thread.sleep(1000l);

            long employerId = employerIdCF.get();
            if (employerId == 12l)
                return CompletableFuture.completedFuture(100000.0);
            if (employerId == 13l)
                return CompletableFuture.completedFuture(1445000.0);
            if (employerId == 101l)
                return CompletableFuture.completedFuture(90000.0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }
}
