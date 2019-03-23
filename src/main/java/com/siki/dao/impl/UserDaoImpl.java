package com.siki.dao.impl;

import com.siki.annotation.SikiRepository;
import com.siki.dao.UserDao;
import com.siki.pojo.User;

/**
 * @author yxzheng
 * @create 2019/3/23
 */
@SikiRepository
public class UserDaoImpl implements UserDao {

    @Override
    public User getUser() {
        User user = new User();
        user.setId("11111");
        user.setName("张三");
        user.setAge(11);
        return user;
    }
}
