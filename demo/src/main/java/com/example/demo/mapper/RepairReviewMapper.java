package com.example.demo.mapper;

import com.example.demo.po.RepairReview;
import java.util.List;

public interface RepairReviewMapper {

    /* 新增并返回主键 */
    int insert(RepairReview record);

    /* 根据主键查询 */
    RepairReview selectByPrimaryKey(Integer repairReviewId);

    /* 查询全部 */
    List<RepairReview> selectAll();

    /* 根据维修单id查询评价列表 */
    List<RepairReview> selectByRepairId( Integer repairId);

    /* 动态更新 */
    int updateByPrimaryKeySelective(RepairReview record);

    /* 根据主键删除 */
    int deleteByPrimaryKey(Integer repairReviewId);
}