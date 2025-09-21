package com.example.demo.controlle;


import com.example.demo.po.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/to_home")
    public String toHome() {
        return "includes/home";
    }

    @RequestMapping("/to_login")
    public String toLogin() {
        return "includes/login-reg";
    }


    /*    public String login(User user, int roleId, HttpSession session, Model model){
            if (!userService.isValidatedUser(user)){
                model.addAttribute("login_error","登录未成功！");
                return "includes/login";
            }
            System.out.println(roleId);
            session.setAttribute("loginName",user.getUsername());
            return "includes/home";
        }*/
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
        System.out.println("来到了a");
        if (ok) {
            return ResponseEntity.ok(Map.of("success", true));
        } else {
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false,
                            "msg", "手机号或用户名已存在"));
        }
    }



}
