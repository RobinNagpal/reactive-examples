package com.echofex.futures.java8_old.service.impl;

import com.echofex.futures.java8_old.service.EmploymentService;
import com.echofex.model.Employer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by robin on 3/7/16.
 */
public class EmploymentServiceImpl implements EmploymentService {
    public List<Employer> findEmployersForUserInYear(long userId, String year) {

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

    public Double getYearlyEarningForUserWithEmployer(long userId, long employerId) {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(employerId == 12l)
            return 100000.0;
       if(employerId == 13l)
            return 1445000.0;
       if(employerId == 101l)
            return 90000.0;

        throw new IllegalArgumentException();
    }
}
