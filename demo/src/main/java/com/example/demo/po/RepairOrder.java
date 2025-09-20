package com.example.demo.po;

import java.time.LocalDateTime;

/**
 * 维修工单实体类
 * 对应数据库表: repair_order
 */
public class RepairOrder {
    private Integer repairId;
    private Integer houseId;
    private Integer tenantId;
    private Integer repairStaffId;
    private String description;
    private String status; // pending, assigned, in_progress, completed
    private LocalDateTime createTime;

    // 构造方法
    public RepairOrder() {}

    public RepairOrder(Integer repairId, Integer houseId, Integer tenantId,
                       Integer repairStaffId, String description, String status,
                       LocalDateTime createTime) {
        this.repairId = repairId;
        this.houseId = houseId;
        this.tenantId = tenantId;
        this.repairStaffId = repairStaffId;
        this.description = description;
        this.status = status;
        this.createTime = createTime;
    }

    // Getter和Setter
    public Integer getRepairId() { return repairId; }
    public void setRepairId(Integer repairId) { this.repairId = repairId; }
    public Integer getHouseId() { return houseId; }
    public void setHouseId(Integer houseId) { this.houseId = houseId; }
    public Integer getTenantId() { return tenantId; }
    public void setTenantId(Integer tenantId) { this.tenantId = tenantId; }
    public Integer getRepairStaffId() { return repairStaffId; }
    public void setRepairStaffId(Integer repairStaffId) { this.repairStaffId = repairStaffId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }

    @Override
    public String toString() {
        return "RepairOrder{repairId=" + repairId + ", houseId=" + houseId +
                ", tenantId=" + tenantId + ", repairStaffId=" + repairStaffId +
                ", description='" + description + "', status='" + status +
                "', createTime=" + createTime + "}";
    }
}