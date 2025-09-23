package com.example.demo.service;

import com.example.demo.po.User;

public interface UserService {
    boolean isValidatedUser(User user);
    /* 根据用户名查询（唯一） */
    boolean selectByUsername(User user);
    /* 根据手机号查询（唯一） */
    boolean selectByPhone(User user);


}
