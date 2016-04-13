package com.echofex.httpservices.impl;

import com.echofex.httpservices.HttpUserService;
import com.echofex.model.User;

/**
 * Created by robin on 3/7/16.
 */
public class HttpUserServiceImpl implements HttpUserService {

    public User getUserForId(long id) {
        return new User();
    }
}
