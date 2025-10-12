package com.example.demo.service.servceImpl;

import com.example.demo.common.LoginUser;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.po.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean isValidatedUser(User user) {
        User user1 = userMapper.selectByUsername(user.getUsername());
        if (user1 == null) return false;
        if (!user1.getPassword().equals(user.getPassword())) return false;

        LoginUser.setLoginUserId(user1.getUserId());
        System.out.println("登录者id：" + LoginUser.getLoginUserId());
        return true;
    }

    @Override
    public boolean selectByUsername(User user) {
        return userMapper.selectByUsername(user.getUsername()) == null;
    }

    @Override
    public User selectByUsername2(String userName) {
        return userMapper.selectByUsername(userName);
    }

    @Override
    public boolean selectByPhone(User user) {
        System.out.println(user.toString());
        if (userMapper.selectByPhone(user.getPhone()) == null && selectByUsername(user)) {
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

    // 验证密码（你原来自带的一段逻辑，我帮你补全方法签名）
    public User validatePassword(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        String encryptedPwd = DigestUtils.md5DigestAsHex(
                password.getBytes(StandardCharsets.UTF_8));
        if (!encryptedPwd.equals(user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        return user;
    }

    @Override
    @Transactional
    public boolean updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public int selectCountByLandlordId(Integer landlordId) {
        return userMapper.selectCountByLandlordId(landlordId);
    }
}