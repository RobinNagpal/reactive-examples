package com.echofex.futures.java5concurrent.service.impl;

import com.echofex.futures.java5concurrent.service.EmploymentService;
import com.echofex.futures.java5concurrent.service.ExecutorService;
import com.echofex.model.Employer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by robin on 3/7/16.
 */
public class EmploymentServiceImpl implements EmploymentService {

    @Autowired
    private ExecutorService executorService;

    public Future<List<Employer>> findEmployersForUserInYear(final Future<Long> userId,final Future<String> year) {

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

        Future<List<Employer>> future = executorService.submitTask(worker);
        return future;
    }

    public Future<Double> getYearlyEarningForUserWithEmployer(Future<Long> userId,final Future<Long> employerId) {



        Callable<Double> worker = new Callable<Double>() {

            public Double call() throws Exception {

                long empId = employerId.get();


                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (empId == 12l)
                    return Math.random() * 100000.0;
                if (empId == 13l)
                    return Math.random() * 1445000.0;
                if (empId == 101l)
                    return Math.random() * 90000.0;

                throw new IllegalArgumentException();
            }
        };

        Future<Double> future = executorService.submitTask(worker);
        return future;
    }
}
