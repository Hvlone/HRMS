package com.example.demo.po;

import java.time.LocalDateTime;

/**
 * 系统用户实体类
 * 对应数据库表: user
 */
public class User {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名（唯一）
     */
    private String username;

    /**
     * 加密密码
     */
    private String password;

    /**
     * 手机号（唯一）
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色类型：landlord(房东), tenant(租客), admin(管理员), repair_staff(维修人员)
     */
    private String userType;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    // 构造方法
    public User() {
    }

    public User(Integer userId, String username, String password, String phone,
                String email, String userType, String realName, LocalDateTime createTime) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.userType = userType;
        this.realName = realName;
        this.createTime = createTime;
    }

    // Getter 和 Setter 方法
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", realName='" + realName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}