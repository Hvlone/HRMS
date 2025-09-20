package com.example.demo.mapper;


import com.example.demo.po.RentOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RentOrderMapper {
    /**
     * 插入订单记录
     * @param order 订单实体
     * @return 影响行数
     */
    @Insert("INSERT INTO rent_order(house_id, tenant_id, amount, payment_method, payment_time, lease_start, lease_end) " +
            "VALUES(#{houseId}, #{tenantId}, #{amount}, #{paymentMethodValue}, #{paymentTime}, #{leaseStart}, #{leaseEnd})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    int insert(RentOrder order);
    
    /**
     * 根据ID查询订单
     * @param orderId 订单ID
     * @return 订单实体
     */
    @Select("SELECT * FROM rent_order WHERE order_id = #{orderId}")
    RentOrder selectById(Integer orderId);
    
    /**
     * 根据租客ID查询订单
     * @param tenantId 租客ID
     * @return 订单列表
     */
    List<RentOrder> selectByTenantId(Integer tenantId);
    
    /**
     * 根据房源ID查询订单
     * @param houseId 房源ID
     * @return 订单列表
     */
    List<RentOrder> selectByHouseId(Integer houseId);

    /**
     * 批量插入订单
     * @param orders 订单列表
     * @return 影响行数
     */
    int batchInsert(@Param("list") List<RentOrder> orders);
}
