package com.example.demo.controller;

import com.example.demo.po.RentOrder;
import com.example.demo.service.RentOrderService;
import com.example.demo.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("https://files.metaso.cn/api/orders")
public class RentOrderController {
    private final RentOrderService rentOrderService;

    @Autowired
    public RentOrderController(RentOrderService rentOrderService) {
        this.rentOrderService = rentOrderService;
    }

    /**
     * 创建租赁订单
     */
    @PostMapping
    public ApiResponse createOrder(@RequestBody RentOrder order) {
        try {
            boolean success = rentOrderService.createRentOrder(order);
            return success ? ApiResponse.success("订单创建成功", order) : 
                           ApiResponse.error("订单创建失败");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 批量创建租赁订单
     */
    @PostMapping("/batch")
    public ApiResponse batchCreateOrders(@RequestBody List<RentOrder> orders) {
        try {
            boolean success = rentOrderService.batchCreateOrders(orders);
            return success ? ApiResponse.success("批量创建订单成功", orders) : 
                           ApiResponse.error("批量创建订单失败");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 获取订单详情
     */
    @GetMapping("/{orderId}")
    public ApiResponse getOrderDetail(@PathVariable Integer orderId) {
        RentOrder order = rentOrderService.getOrderDetail(orderId);
        if (order != null) {
            return ApiResponse.success(order);
        }
        return ApiResponse.error("订单不存在");
    }

    /**
     * 获取租客的订单列表
     */
    @GetMapping("/tenant/{tenantId}")
    public ApiResponse getTenantOrders(@PathVariable Integer tenantId) {
        List<RentOrder> orders = rentOrderService.getOrdersByTenant(tenantId);
        return ApiResponse.success(orders);
    }

    /**
     * 获取房源的订单列表
     */
    @GetMapping("/house/{houseId}")
    public ApiResponse getHouseOrders(@PathVariable Integer houseId) {
        List<RentOrder> orders = rentOrderService.getOrdersByHouse(houseId);
        return ApiResponse.success(orders);
    }
}