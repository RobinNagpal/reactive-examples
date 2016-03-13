package com.echofex.futures.java8_old.service.impl;

import com.echofex.futures.java8_old.service.UserService;
import com.echofex.model.User;

/**
 * Created by robin on 3/7/16.
 */
public class UserServiceImpl implements UserService {

    public User getUserForId(long id) {
        return new User();
    }
}
