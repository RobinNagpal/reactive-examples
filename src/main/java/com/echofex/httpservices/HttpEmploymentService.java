package com.echofex.httpservices;

import com.echofex.model.Employer;

import java.util.List;

/**
 * Created by robin on 3/6/16.
 */
public interface HttpEmploymentService {

    List<Employer> findEmployersForUserInYear(long userId, String year);

    Double getYearlyEarningForUserWithEmployer(long userId, long employerId);
}
