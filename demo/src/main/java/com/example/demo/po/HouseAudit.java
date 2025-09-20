package com.example.demo.po;


import java.util.Date;

public class HouseAudit {

    private Integer auditId;
    private Integer houseId;
    private Integer adminId;
    private AuditResult auditResult; // 审核结果：approved/rejected
    private Date auditTime;

    public enum AuditResult {
        APPROVED, REJECTED
    }

    public HouseAudit() {}

    public HouseAudit(Integer houseId, Integer adminId, AuditResult auditResult) {
        this.houseId = houseId;
        this.adminId = adminId;
        this.auditResult = auditResult;
        this.auditTime = new Date();
    }

    public Integer getAuditId() { return auditId; }
    public void setAuditId(Integer auditId) { this.auditId = auditId; }
    
    public Integer getHouseId() { return houseId; }
    public void setHouseId(Integer houseId) { this.houseId = houseId; }
    
    public Integer getAdminId() { return adminId; }
    public void setAdminId(Integer adminId) { this.adminId = adminId; }
    
    public AuditResult getAuditResult() { return auditResult; }
    public void setAuditResult(AuditResult auditResult) { this.auditResult = auditResult; }
    
    public String getAuditResultValue() {
        return auditResult != null ? auditResult.name().toLowerCase() : null;
    }
    
    public Date getAuditTime() { return auditTime; }
    public void setAuditTime(Date auditTime) { this.auditTime = auditTime; }


    @Override
    public String toString() {
        return "HouseAudit{" +
                "auditId=" + auditId +
                ", houseId=" + houseId +
                ", auditResult=" + auditResult +
                '}';
    }
}