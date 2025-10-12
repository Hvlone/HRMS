package com.example.demo.mapper;

import com.example.demo.po.User;
import org.apache.ibatis.annotations.Mapper;

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
    int updateByPrimaryKeySelective(User user);

    /* 根据主键删除 */
    int deleteByPrimaryKey(Integer userId);

    int selectCountByLandlordId(Integer landlordId);

    String selectUsertypeByUserId(Integer userId);
}