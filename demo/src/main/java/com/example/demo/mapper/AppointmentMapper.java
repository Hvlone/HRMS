package com.example.demo.mapper;

import com.example.demo.po.Appointment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppointmentMapper {
    /**
     * 插入预约记录
     * @param appointment 预约实体
     * @return 影响行数
     */
    @Insert("INSERT INTO appointment(house_id, tenant_id, appoint_time, status, create_time) " +
            "VALUES(#{houseId}, #{tenantId}, #{appointTime}, #{statusValue}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "appointId")
    int insert(Appointment appointment);
    
    /**
     * 更新预约状态
     * @param appointId 预约ID
     * @param status 新状态
     * @return 影响行数
     */
    @Update("UPDATE appointment SET status = #{statusValue} WHERE appoint_id = #{appointId}")
    int updateStatus(@Param("appointId") Integer appointId,
                     @Param("status") Appointment.Status status);
    
    /**
     * 根据ID查询预约
     * @param appointId 预约ID
     * @return 预约实体
     */
    @Select("SELECT * FROM appointment WHERE appoint_id = #{appointId}")
    Appointment selectById(Integer appointId);
    
    /**
     * 根据租客ID查询预约
     * @param tenantId 租客ID
     * @return 预约列表
     */
    List<Appointment> selectByTenantId(Integer tenantId);
    
    /**
     * 根据房源ID查询预约
     * @param houseId 房源ID
     * @return 预约列表
     */
    List<Appointment> selectByHouseId(Integer houseId);
}