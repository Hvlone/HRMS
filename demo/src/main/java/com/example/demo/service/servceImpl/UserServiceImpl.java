package com.example.demo.service.servceImpl;

import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.po.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public boolean register(User user) {
        try {
            // 密码加密处理
            String encryptedPwd = DigestUtils.md5DigestAsHex(
                    user.getPassword().getBytes(StandardCharsets.UTF_8));
            user.setPassword(encryptedPwd);

            return userMapper.insert(user) > 0;
        } catch (DuplicateKeyException e) {
            throw new BusinessException("用户名或手机号已存在");
        }
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 验证密码
        String encryptedPwd = DigestUtils.md5DigestAsHex(
                password.getBytes(StandardCharsets.UTF_8));
        if (!encryptedPwd.equals(user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        return user;
    }

    @Override
    @Transactional
    public boolean updateUserInfo(User user) {
        return userMapper.update(user) > 0;
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}