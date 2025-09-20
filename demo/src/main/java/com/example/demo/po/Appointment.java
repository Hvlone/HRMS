package com.example.demo.po;

import java.util.Date;

public class Appointment {

    private Integer appointId;
    private Integer houseId;
    private Integer tenantId;
    private Date appointTime;
    private Status status; // 状态：pending/confirmed/canceled
    private Date createTime;

    public enum Status {
        PENDING, CONFIRMED, CANCELED
    }

    public Appointment() {}

    public Appointment(Integer houseId, Integer tenantId, Date appointTime) {
        this.houseId = houseId;
        this.tenantId = tenantId;
        this.appointTime = appointTime;
        this.status = Status.PENDING;
    }

    public Integer getAppointId() { return appointId; }
    public void setAppointId(Integer appointId) { this.appointId = appointId; }

    public Integer getHouseId() { return houseId; }
    public void setHouseId(Integer houseId) { this.houseId = houseId; }

    public Integer getTenantId() { return tenantId; }
    public void setTenantId(Integer tenantId) { this.tenantId = tenantId; }

    public Date getAppointTime() { return appointTime; }
    public void setAppointTime(Date appointTime) { this.appointTime = appointTime; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getStatusValue() {
        return status != null ? status.name().toLowerCase() : null;
    }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointId=" + appointId +
                ", houseId=" + houseId +
                ", status=" + status +
                '}';
    }
}