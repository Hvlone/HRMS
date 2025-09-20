package com.example.demo.service;

import com.example.demo.po.Appointment;

import java.util.List;

public interface AppointmentService {
    /**
     * 创建预约
     * @param appointment 预约信息
     * @return 创建结果
     */
    boolean createAppointment(Appointment appointment);
    
    /**
     * 更新预约状态
     * @param appointId 预约ID
     * @param status 新状态
     * @return 更新结果
     */
    boolean updateAppointmentStatus(Integer appointId, Appointment.Status status);
    
    /**
     * 获取预约详情
     * @param appointId 预约ID
     * @return 预约详情
     */
    Appointment getAppointmentDetail(Integer appointId);
    
    /**
     * 获取租客的预约列表
     * @param tenantId 租客ID
     * @return 预约列表
     */
    List<Appointment> getAppointmentsByTenant(Integer tenantId);
    
    /**
     * 获取房源的预约列表
     * @param houseId 房源ID
     * @return 预约列表
     */
    List<Appointment> getAppointmentsByHouse(Integer houseId);
}