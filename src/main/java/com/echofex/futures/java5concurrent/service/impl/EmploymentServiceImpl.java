package com.echofex.futures.java5concurrent.service.impl;

import com.echofex.futures.java5concurrent.service.EmploymentService;
import com.echofex.model.Employer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by robin on 3/7/16.
 */
public class EmploymentServiceImpl implements EmploymentService {

    public Future<List<Employer>> findEmployersForUserInYear(final Future<Long> userId, final Future<String> year) {

        Callable<List<Employer>> worker = new Callable<List<Employer>>() {


            public List<Employer> call() throws Exception {

                long uId = userId.get();
                String yearValue = year.get();

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


                return employers;
            }
        };

        Future<List<Employer>> future = ExecutorServiceImpl.executor.submit(worker);
        return future;
    }

    public Future<Double> getYearlyEarningForUserWithEmployer(final Future<Long> userId, final Future<Long> employerId) {


        Callable<Double> worker = new Callable<Double>() {

            public Double call() throws Exception {

                Long userID = userId.get();
                long empId = employerId.get();


                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                if(empId == 12l)
                    return 100000.0;
                if(empId == 13l)
                    return 1445000.0;
                if(empId == 101l)
                    return 90000.0;

                throw new IllegalArgumentException();
            }
        };

        Future<Double> future = ExecutorServiceImpl.executor.submit(worker);
        return future;
    }
}
