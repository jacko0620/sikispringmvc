package com.siki.controller;

import com.siki.annotation.SikiAutowired;
import com.siki.annotation.SikiController;
import com.siki.annotation.SikiRequestMapping;
import com.siki.annotation.SikiRequestParam;
import com.siki.pojo.User;
import com.siki.service.UserService;

/**
 * @author yxzheng
 * @create 2019/3/23
 */
@SikiController
@SikiRequestMapping("/user")
public class UserController {

    @SikiAutowired
    private UserService userService;

    @SikiRequestMapping("/getUser")
    public void getUser(@SikiRequestParam(value = "id") String userId){
        User user = userService.getUser();
    }
}
