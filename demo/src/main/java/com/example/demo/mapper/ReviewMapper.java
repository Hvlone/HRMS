package com.example.demo.mapper;

import com.example.demo.po.Review;
import java.util.List;

public interface ReviewMapper {

    /* 新增并返回主键 */
    int insert(Review record);

    /* 根据主键查询 */
    Review selectByPrimaryKey(Integer reviewId);

    /* 查询全部 */
    List<Review> selectAll();

    /* 根据房源ID查询评论列表 */
    List<Review> selectByHouseId(Integer houseId);

    /* 根据租户ID查询评论列表 */
    List<Review> selectByTenantId( Integer tenantId);

    /* 动态更新 */
    int updateByPrimaryKeySelective(Review record);

    /* 根据主键删除 */
    int deleteByPrimaryKey(Integer reviewId);
}