package com.example.demo.po;

import java.time.LocalDateTime;

/**
 * 看房预约实体类
 * 对应数据库表: appointment
 */
public class Appointment {
    private Integer appointId;
    private Integer houseId;
    private Integer tenantId;
    private LocalDateTime appointTime;
    private String status; // pending, confirmed, canceled
    private LocalDateTime createTime;

    // 构造方法
    public Appointment() {}

    public Appointment(Integer appointId, Integer houseId, Integer tenantId,
                       LocalDateTime appointTime, String status, LocalDateTime createTime) {
        this.appointId = appointId;
        this.houseId = houseId;
        this.tenantId = tenantId;
        this.appointTime = appointTime;
        this.status = status;
        this.createTime = createTime;
    }

    // Getter和Setter
    public Integer getAppointId() { return appointId; }
    public void setAppointId(Integer appointId) { this.appointId = appointId; }
    public Integer getHouseId() { return houseId; }
    public void setHouseId(Integer houseId) { this.houseId = houseId; }
    public Integer getTenantId() { return tenantId; }
    public void setTenantId(Integer tenantId) { this.tenantId = tenantId; }
    public LocalDateTime getAppointTime() { return appointTime; }
    public void setAppointTime(LocalDateTime appointTime) { this.appointTime = appointTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }

    @Override
    public String toString() {
        return "Appointment{appointId=" + appointId + ", houseId=" + houseId +
                ", tenantId=" + tenantId + ", appointTime=" + appointTime +
                ", status='" + status + "', createTime=" + createTime + "}";
    }
}