package com.example.demo.controller;


import com.example.demo.po.User;
import com.example.demo.service.HouseService;
import com.example.demo.service.UserService;
import com.example.demo.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    HouseService houseService;

    @RequestMapping("/to_home")
    public String toHome() {
        return "includes/home";
    }

    @RequestMapping("/to_login")
    public String toLogin() {
        return "includes/login-reg";
    }

    @RequestMapping("/to_test")
    public String toTest() {
        return "includes/test";
    }

    @PostMapping("/to_aboutSelf")
    @ResponseBody
    public ApiResponse about_self(HttpSession session,Model model){
        User user=userService.selectByUsername2((String)session.getAttribute("loginName"));
        model.addAttribute("user",user);
        model.addAttribute("landlord_id",12138+user.getUserId()+"");
        int res=houseService.selectHouseCount(user.getUserId());
        System.out.println("aaa");
        model.addAttribute("res",res);
        int count=userService.selectCountByLandlordId(user.getUserId());
        model.addAttribute("count",count);
        System.out.println("user:"+user.getUserId()+"，"+model.getAttribute("landlord_id"));
        return ApiResponse.success(user.getUserType());
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody User user, HttpSession session, Model model) {
        boolean ok = userService.isValidatedUser(user);
        if (!ok) {
            model.addAttribute("login_error", "登录未成功！");
        } else {
            session.setAttribute("loginName", user.getUsername());
        }
        return Collections.singletonMap("success", ok);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        boolean ok = userService.selectByPhone(user);
        if (ok) {
            return ResponseEntity.ok(Map.of("success", true));
        } else {
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false,
                            "msg", "手机号或用户名已存在"));
        }
    }

    @PostMapping("/to_edit_user")
    @ResponseBody
    public ApiResponse editUser(int userId){
        User user=userService.getUserById(userId);
        if (user!=null){
            return ApiResponse.success("修改成功！",user);
        }
        else {
            return ApiResponse.error("修改失败！");
        }
    }

    @PostMapping("/save_user")
    @ResponseBody
    public ApiResponse saveUser(@RequestBody User user,HttpSession session){
        User user1=userService.selectByUsername2((String)session.getAttribute("loginName"));
        user.setUserId(user1.getUserId());
        boolean res=userService.updateByPrimaryKeySelective(user);
        if (res){
            return ApiResponse.success("修改成功！");
        }
        else {
            return ApiResponse.error("修改失败！");
        }
    }
}
