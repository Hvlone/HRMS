package com.example.demo.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.po.User;
<<<<<<< HEAD
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
=======
import java.util.List;
@Mapper
public interface UserMapper {

    /* 新增并返回主键 */
    int insert(User record);

    /* 根据主键查询 */
    User selectByPrimaryKey(Integer userId);

    /* 根据用户名查询（唯一） */
    User selectByUsername(String username);

    /* 根据手机号查询（唯一） */
    User selectByPhone(String phone);

    /* 查询全部 */
    List<User> selectAll();

    /* 根据角色类型查询 */
    List<User> selectByUserType(String userType);

    /* 动态更新 */
    int updateByPrimaryKeySelective(User record);

    /* 根据主键删除 */
    int deleteByPrimaryKey(Integer userId);
}
>>>>>>> fc76c76c7b6f35ac52a28a3fe55750a6792c1b7f
