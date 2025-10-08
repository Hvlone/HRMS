package com.example.demo.mapper;

import com.example.demo.po.RepairOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RepairOrderMapper {

    /* 新增并返回主键 */
    int insert(RepairOrder record);

    /* 根据主键查询 */
    RepairOrder selectByPrimaryKey(Integer repairId);

    /* 查询全部 */
    List<RepairOrder> selectAll();

    /* 根据主键动态更新 */
    int updateByPrimaryKeySelective(RepairOrder record);

    /* 根据主键删除 */
    int deleteByPrimaryKey(Integer repairId);
}