package com.example.demo.service.servceImpl;

import com.example.demo.common.LoginUser;
import com.example.demo.common.MessageConstant;
import com.example.demo.mapper.UserMapper;
import com.example.demo.po.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean isValidatedUser(User user) {
        User user1=userMapper.selectByUsername(user.getUsername());
        if (user1==null)
            return false;
        if (!user1.getPassword().equals(user.getPassword()))
            return false;
        LoginUser.setLoginUserId(user1.getUserId());
        System.out.println("登录者id："+LoginUser.getLoginUserId());
        return true;
    }

    @Override
    public boolean selectByUsername(User user) {
        if (userMapper.selectByUsername(user.getUsername())==null)
            return true;
        return false;
    }

    @Override
    public User selectByUsername2(String userName) {
        return userMapper.selectByUsername(userName);
    }

    @Override
    public boolean selectByPhone(User user) {
        System.out.println(user.toString());
        if(userMapper.selectByPhone(user.getPhone())==null&&selectByUsername(user)){
            user.setCreateTime(LocalDateTime.now());
            userMapper.insert(user);
            return true;
        }
        return false;
    }

    @Override
    public String selectUsertypeByUserId(Integer userId) {
        return userMapper.selectUsertypeByUserId(userId);
    }


}
