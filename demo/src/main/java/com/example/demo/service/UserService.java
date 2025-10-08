package com.example.demo.service;

import com.example.demo.po.User;

public interface UserService {
    boolean isValidatedUser(User user);
    /* 根据用户名查询（唯一） */
    boolean selectByUsername(User user);
    /* 根据手机号查询（唯一） */
    boolean selectByPhone(User user);


<<<<<<< HEAD
    // 更新用户信息
    boolean updateUserInfo(User user);

    // 根据ID查询用户
    User getUserById(Integer userId);

    // 根据用户名查询用户
    User getUserByUsername(String username);
}
=======
}
>>>>>>> fc76c76c7b6f35ac52a28a3fe55750a6792c1b7f
