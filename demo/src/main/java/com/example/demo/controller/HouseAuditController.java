package com.example.demo.controller;

import com.example.demo.po.HouseAudit;
import com.example.demo.service.HouseAuditService;
import com.example.demo.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("https://files.metaso.cn/api/audits")
public class HouseAuditController {
    private final HouseAuditService auditService;

    @Autowired
    public HouseAuditController(HouseAuditService auditService) {
        this.auditService = auditService;
    }

    /**
     * 创建审核记录
     */
    @PostMapping
    public ApiResponse createAudit(@RequestBody HouseAudit audit) {
        try {
            boolean success = auditService.createAuditRecord(audit);
            return success ? ApiResponse.success("审核记录创建成功") : 
                           ApiResponse.error("审核记录创建失败");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 获取房源审核历史
     */
    @GetMapping("/house/{houseId}")
    public ApiResponse getAuditHistory(@PathVariable Integer houseId) {
        List<HouseAudit> audits = auditService.getAuditHistory(houseId);
        return ApiResponse.success(audits);
    }

    /**
     * 根据结果查询审核记录
     */
    @GetMapping("/result/{result}")
    public ApiResponse getAuditsByResult(
            @PathVariable String result) {
        try {
            HouseAudit.AuditResult auditResult = 
                HouseAudit.AuditResult.valueOf(result.toUpperCase());
            List<HouseAudit> audits = auditService.getAuditsByResult(auditResult);
            return ApiResponse.success(audits);
        } catch (IllegalArgumentException e) {
            return ApiResponse.error("无效的审核结果类型");
        }
    }
}