package com.example.demo.po;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 租赁订单实体类
 * 对应数据库表: rent_order
 */
public class RentOrder {
    private Integer orderId;
    private Integer houseId;
    private Integer tenantId;
    private BigDecimal amount;
    private String paymentMethod; // alipay, wechat, bank
    private LocalDateTime paymentTime;
    private LocalDate leaseStart;
    private LocalDate leaseEnd;

    // 构造方法
    public RentOrder() {}

    public RentOrder(Integer orderId, Integer houseId, Integer tenantId,
                     BigDecimal amount, String paymentMethod, LocalDateTime paymentTime,
                     LocalDate leaseStart, LocalDate leaseEnd) {
        this.orderId = orderId;
        this.houseId = houseId;
        this.tenantId = tenantId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentTime = paymentTime;
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;
    }

    // Getter和Setter
    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public Integer getHouseId() { return houseId; }
    public void setHouseId(Integer houseId) { this.houseId = houseId; }
    public Integer getTenantId() { return tenantId; }
    public void setTenantId(Integer tenantId) { this.tenantId = tenantId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public LocalDateTime getPaymentTime() { return paymentTime; }
    public void setPaymentTime(LocalDateTime paymentTime) { this.paymentTime = paymentTime; }
    public LocalDate getLeaseStart() { return leaseStart; }
    public void setLeaseStart(LocalDate leaseStart) { this.leaseStart = leaseStart; }
    public LocalDate getLeaseEnd() { return leaseEnd; }
    public void setLeaseEnd(LocalDate leaseEnd) { this.leaseEnd = leaseEnd; }

    @Override
    public String toString() {
        return "RentOrder{orderId=" + orderId + ", houseId=" + houseId +
                ", tenantId=" + tenantId + ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + "', paymentTime=" + paymentTime +
                ", leaseStart=" + leaseStart + ", leaseEnd=" + leaseEnd + "}";
    }
}