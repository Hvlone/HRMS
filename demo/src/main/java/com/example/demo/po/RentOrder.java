package com.example.demo.po;

import java.math.BigDecimal;
import java.util.Date;

public class RentOrder {

    private Integer orderId;
    private Integer houseId;
    private Integer tenantId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod; // 支付方式：alipay/wechat/bank
    private Date paymentTime;
    private Date leaseStart;
    private Date leaseEnd;

    public enum PaymentMethod {
        ALIPAY, WECHAT, BANK
    }

    public RentOrder() {}

    public RentOrder(Integer houseId, Integer tenantId, BigDecimal amount, PaymentMethod paymentMethod, Date leaseStart, Date leaseEnd) {
        this.houseId = houseId;
        this.tenantId = tenantId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;
        this.paymentTime = new Date();
    }


    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    
    public Integer getHouseId() { return houseId; }
    public void setHouseId(Integer houseId) { this.houseId = houseId; }
    
    public Integer getTenantId() { return tenantId; }
    public void setTenantId(Integer tenantId) { this.tenantId = tenantId; }
    
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { 
        this.paymentMethod = paymentMethod; 
    }
    
    // 获取支付方式的数据库兼容值
    public String getPaymentMethodValue() {
        return paymentMethod != null ? paymentMethod.name().toLowerCase() : null;
    }
    
    public Date getPaymentTime() { return paymentTime; }
    public void setPaymentTime(Date paymentTime) { this.paymentTime = paymentTime; }
    
    public Date getLeaseStart() { return leaseStart; }
    public void setLeaseStart(Date leaseStart) { this.leaseStart = leaseStart; }
    
    public Date getLeaseEnd() { return leaseEnd; }
    public void setLeaseEnd(Date leaseEnd) { this.leaseEnd = leaseEnd; }

    @Override
    public String toString() {
        return "RentOrder{" +
                "orderId=" + orderId +
                ", amount=" + amount +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}