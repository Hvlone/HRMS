package com.example.demo.service;

import com.example.demo.po.User;


public interface UserService {
    // 用户注册
    boolean register(User user);

    // 用户登录
    User login(String username, String password);

    // 更新用户信息
    boolean updateUserInfo(User user);

    // 根据ID查询用户
    User getUserById(Integer userId);

    // 根据用户名查询用户
    User getUserByUsername(String username);
}