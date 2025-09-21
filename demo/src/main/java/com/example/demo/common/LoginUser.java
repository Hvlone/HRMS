package com.example.demo.common;

public class LoginUser  {

    public static ThreadLocal<Integer> loginUser=new ThreadLocal<>();
    public static void setLoginUserId(Integer id){
        loginUser.set(id);
    }
    public static Integer getLoginUserId(){
        return loginUser.get();
    }
    public static void removeLoginUserId(){
        loginUser.remove();

    }}
