package com.siki.service.impl;

import com.siki.annotation.SikiAutowired;
import com.siki.annotation.SikiService;
import com.siki.dao.UserDao;
import com.siki.pojo.User;
import com.siki.service.UserService;

/**
 * @author yxzheng
 * @create 2019/3/23
 */
@SikiService
public class UserServiceImpl implements UserService {

    @SikiAutowired
    private UserDao userDao;

    @Override
    public User getUser() {
        return userDao.getUser();
    }
}
