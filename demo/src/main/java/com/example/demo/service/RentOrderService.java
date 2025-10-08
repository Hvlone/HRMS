package com.example.demo.service;

import com.example.demo.po.RentOrder;

import java.util.List;

public interface RentOrderService {
    /**
     * 创建租赁订单
     * @param order 订单信息
     * @return 创建结果
     */
    boolean createRentOrder(RentOrder order);
    
    /**
     * 获取订单详情
     * @param orderId 订单ID
     * @return 订单详情
     */
    RentOrder getOrderDetail(Integer orderId);
    
    /**
     * 获取租客的订单列表
     * @param tenantId 租客ID
     * @return 订单列表
     */
    List<RentOrder> getOrdersByTenant(Integer tenantId);
    
    /**
     * 获取房源的订单列表
     * @param houseId 房源ID
     * @return 订单列表
     */
    List<RentOrder> getOrdersByHouse(Integer houseId);
    
    /**
     * 批量创建订单
     * @param orders 订单列表
     * @return 创建结果
     */
    boolean batchCreateOrders(List<RentOrder> orders);
}