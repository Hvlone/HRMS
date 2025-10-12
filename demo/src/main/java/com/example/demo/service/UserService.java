package com.example.demo.service;

import com.example.demo.po.User;

public interface UserService {
    boolean isValidatedUser(User user);
    /* 根据用户名查询（唯一） */
    boolean selectByUsername(User user);

    User selectByUsername2(String userName);
    /* 根据手机号查询（唯一） */
    boolean selectByPhone(User user);

    String selectUsertypeByUserId(Integer userId);

    // 更新用户信息
    boolean updateByPrimaryKeySelective(User user);

    // 根据ID查询用户
    User getUserById(Integer userId);

    // 根据用户名查询用户
    User getUserByUsername(String username);

    //查询房东有多个租客
    int selectCountByLandlordId(Integer landlordId);


}



