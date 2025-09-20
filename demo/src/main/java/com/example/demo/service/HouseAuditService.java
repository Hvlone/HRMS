package com.example.demo.service;

import com.example.demo.po.HouseAudit;

import java.util.List;

public interface HouseAuditService {
    /**
     * 创建审核记录
     * @param audit 审核实体
     * @return 创建结果
     */
    boolean createAuditRecord(HouseAudit audit);
    
    /**
     * 获取房源审核历史
     * @param houseId 房源ID
     * @return 审核记录列表
     */
    List<HouseAudit> getAuditHistory(Integer houseId);
    
    /**
     * 根据结果查询审核记录
     * @param result 审核结果
     * @return 审核记录列表
     */
    List<HouseAudit> getAuditsByResult(HouseAudit.AuditResult result);
}