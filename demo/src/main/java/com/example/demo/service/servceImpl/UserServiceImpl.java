package com.example.demo.service.servceImpl;

<<<<<<< HEAD
import com.example.demo.exception.BusinessException;
=======
import com.example.demo.common.LoginUser;
import com.example.demo.common.MessageConstant;
>>>>>>> fc76c76c7b6f35ac52a28a3fe55750a6792c1b7f
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


<<<<<<< HEAD
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
=======
}
>>>>>>> fc76c76c7b6f35ac52a28a3fe55750a6792c1b7f
