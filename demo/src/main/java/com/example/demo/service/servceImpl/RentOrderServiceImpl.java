package com.example.demo.service.servceImpl;

import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.RentOrderMapper;
import com.example.demo.po.RentOrder;
import com.example.demo.service.RentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class RentOrderServiceImpl implements RentOrderService {
    private final RentOrderMapper rentOrderMapper;

    @Autowired
    public RentOrderServiceImpl(RentOrderMapper rentOrderMapper) {
        this.rentOrderMapper = rentOrderMapper;
    }

    @Override
    @Transactional
    public boolean createRentOrder(RentOrder order) {
        // 验证必要参数
        if (order.getHouseId() == null) {
            throw new BusinessException("房源ID不能为空");
        }
        if (order.getTenantId() == null) {
            throw new BusinessException("租客ID不能为空");
        }
        if (order.getAmount() == null || order.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("支付金额必须大于0");
        }
        if (order.getLeaseStart() == null) {
            throw new BusinessException("租期开始时间不能为空");
        }
        if (order.getLeaseEnd() == null) {
            throw new BusinessException("租期结束时间不能为空");
        }
        if (order.getLeaseStart().after(order.getLeaseEnd())) {
            throw new BusinessException("租期开始时间不能晚于结束时间");
        }
        
        // 设置默认支付时间
        if (order.getPaymentTime() == null) {
            order.setPaymentTime(new Date());
        }
        
        return rentOrderMapper.insert(order) > 0;
    }

    @Override
    public RentOrder getOrderDetail(Integer orderId) {
        return rentOrderMapper.selectById(orderId);
    }

    @Override
    public List<RentOrder> getOrdersByTenant(Integer tenantId) {
        return rentOrderMapper.selectByTenantId(tenantId);
    }

    @Override
    public List<RentOrder> getOrdersByHouse(Integer houseId) {
        return rentOrderMapper.selectByHouseId(houseId);
    }

    @Override
    @Transactional
    public boolean batchCreateOrders(List<RentOrder> orders) {
        if (orders == null || orders.isEmpty()) {
            throw new BusinessException("订单列表不能为空");
        }
        
        // 验证所有订单的有效性
        for (RentOrder order : orders) {
            if (order.getHouseId() == null || order.getTenantId() == null) {
                throw new BusinessException("订单缺少必要信息");
            }
            if (order.getPaymentTime() == null) {
                order.setPaymentTime(new Date());
            }
        }
        
        return rentOrderMapper.batchInsert(orders) > 0;
    }
}