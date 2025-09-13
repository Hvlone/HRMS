package com.example.demo.po;
import java.time.LocalDateTime;

/**
 * 房源审核记录实体类
 * 对应数据库表: house_audit
 */
public class HouseAudit {
    private Integer auditId;
    private Integer houseId;
    private Integer adminId;
    private String auditResult; // approved, rejected
    private LocalDateTime auditTime;

    // 构造方法
    public HouseAudit() {}

    public HouseAudit(Integer auditId, Integer houseId, Integer adminId,
                      String auditResult, LocalDateTime auditTime) {
        this.auditId = auditId;
        this.houseId = houseId;
        this.adminId = adminId;
        this.auditResult = auditResult;
        this.auditTime = auditTime;
    }

    // Getter和Setter
    public Integer getAuditId() { return auditId; }
    public void setAuditId(Integer auditId) { this.auditId = auditId; }
    public Integer getHouseId() { return houseId; }
    public void setHouseId(Integer houseId) { this.houseId = houseId; }
    public Integer getAdminId() { return adminId; }
    public void setAdminId(Integer adminId) { this.adminId = adminId; }
    public String getAuditResult() { return auditResult; }
    public void setAuditResult(String auditResult) { this.auditResult = auditResult; }
    public LocalDateTime getAuditTime() { return auditTime; }
    public void setAuditTime(LocalDateTime auditTime) { this.auditTime = auditTime; }

    @Override
    public String toString() {
        return "HouseAudit{auditId=" + auditId + ", houseId=" + houseId +
                ", adminId=" + adminId + ", auditResult='" + auditResult +
                "', auditTime=" + auditTime + "}";
    }
}