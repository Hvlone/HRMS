package com.example.demo.service.servceImpl;

import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.HouseAuditMapper;
import com.example.demo.po.HouseAudit;
import com.example.demo.service.HouseAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class HouseAuditServiceImpl implements HouseAuditService {
    private final HouseAuditMapper auditMapper;

    @Autowired
    public HouseAuditServiceImpl(HouseAuditMapper auditMapper) {
        this.auditMapper = auditMapper;
    }

    @Override
    @Transactional
    public boolean createAuditRecord(HouseAudit audit) {
        // 验证必要参数
        if (audit.getHouseId() == null) {
            throw new BusinessException("房源ID不能为空");
        }
        if (audit.getAdminId() == null) {
            throw new BusinessException("管理员ID不能为空");
        }
        if (audit.getAuditResult() == null) {
            throw new BusinessException("审核结果不能为空");
        }
        
        // 设置默认时间
        if (audit.getAuditTime() == null) {
            audit.setAuditTime(new Date());
        }
        
        return auditMapper.insert(audit) > 0;
    }

    @Override
    public List<HouseAudit> getAuditHistory(Integer houseId) {
        return auditMapper.selectByHouseId(houseId);
    }

    @Override
    public List<HouseAudit> getAuditsByResult(HouseAudit.AuditResult result) {
        return auditMapper.selectByResult(result);
    }
}