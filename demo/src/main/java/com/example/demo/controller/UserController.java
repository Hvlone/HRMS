package com.example.demo.controller;


import com.example.demo.po.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("https://files.metaso.cn/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户注册
     * @param user 用户数据
     * @return 操作结果
     */
    @PostMapping("/register")
    public ApiResponse register(@RequestBody User user) {
        try {
            boolean success = userService.register(user);
            return success ? ApiResponse.success("注册成功") : 
                           ApiResponse.error("注册失败");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @PostMapping("/login")
    public ApiResponse login(
            @RequestParam String username,
            @RequestParam String password) {
        try {
            User user = userService.login(username, password);
            return ApiResponse.success("登录成功", user);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/{userId}")
    public ApiResponse getUser(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return user != null ? ApiResponse.success(user) : 
                            ApiResponse.error("用户不存在");
    }
}
