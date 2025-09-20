package com.example.demo.mapper;

import com.example.demo.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 根据用户ID查询
     * @param userId 用户ID
     * @return 用户实体
     */
    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    User selectById(Integer userId);

    /**
     * 根据用户名查询
     * @param username 用户名
     * @return 用户实体
     */
    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectByUsername(String username);

    /**
     * 插入新用户
     * @param user 用户实体
     * @return 影响行数
     */
    @Insert("INSERT INTO user(username, password, phone, email, user_type, real_name, create_time) " +
            "VALUES(#{username}, #{password}, #{phone}, #{email}, #{userType}, #{realName}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    /**
     * 更新用户信息
     * @param user 用户实体
     * @return 影响行数
     */
    @Update("UPDATE user SET " +
            "phone = #{phone}, " +
            "email = #{email}, " +
            "real_name = #{realName} " +
            "WHERE user_id = #{userId}")
    int update(User user);

    /**
     * 根据条件查询用户
     * @param userType 用户类型
     * @param keyword 关键词
     * @return 用户列表
     */
    List<User> selectByCondition(
            @Param("userType") String userType,
            @Param("keyword") String keyword);
}
