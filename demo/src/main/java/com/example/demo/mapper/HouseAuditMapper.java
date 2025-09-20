package com.example.demo.mapper;

import com.example.demo.po.HouseAudit;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HouseAuditMapper {
    /**
     * 插入审核记录
     * @param audit 审核实体
     * @return 影响行数
     */
    @Insert("INSERT INTO house_audit(house_id, admin_id, audit_result, audit_time) " +
            "VALUES(#{houseId}, #{adminId}, #{auditResultValue}, #{auditTime})")
    @Options(useGeneratedKeys = true, keyProperty = "auditId")
    int insert(HouseAudit audit);
    
    /**
     * 根据房源ID查询审核记录
     * @param houseId 房源ID
     * @return 审核记录列表
     */
    @Select("SELECT * FROM house_audit WHERE house_id = #{houseId}")
    List<HouseAudit> selectByHouseId(Integer houseId);
    
    /**
     * 根据审核结果查询记录
     * @param result 审核结果
     * @return 审核记录列表
     */
    List<HouseAudit> selectByResult(
        @Param("result") HouseAudit.AuditResult result);
}