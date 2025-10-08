package com.example.demo.controller;

import com.example.demo.po.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("https://files.metaso.cn/api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * 创建预约
     */
    @PostMapping
    public ApiResponse createAppointment(@RequestBody Appointment appointment) {
        try {
            boolean success = appointmentService.createAppointment(appointment);
            return success ? ApiResponse.success("预约创建成功", appointment) : 
                           ApiResponse.error("预约创建失败");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 确认预约
     */
    @PutMapping("/{appointId}/confirm")
    public ApiResponse confirmAppointment(@PathVariable Integer appointId) {
        try {
            boolean success = appointmentService.updateAppointmentStatus(
                appointId, Appointment.Status.CONFIRMED);
            return success ? ApiResponse.success("预约已确认") : 
                           ApiResponse.error("预约确认失败");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 取消预约
     */
    @PutMapping("/{appointId}/cancel")
    public ApiResponse cancelAppointment(@PathVariable Integer appointId) {
        try {
            boolean success = appointmentService.updateAppointmentStatus(
                appointId, Appointment.Status.CANCELED);
            return success ? ApiResponse.success("预约已取消") : 
                           ApiResponse.error("预约取消失败");
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * 获取预约详情
     */
    @GetMapping("/{appointId}")
    public ApiResponse getAppointmentDetail(@PathVariable Integer appointId) {
        Appointment appointment = appointmentService.getAppointmentDetail(appointId);
        if (appointment != null) {
            return ApiResponse.success(appointment);
        }
        return ApiResponse.error("预约不存在");
    }

    /**
     * 获取租客的预约列表
     */
    @GetMapping("/tenant/{tenantId}")
    public ApiResponse getTenantAppointments(@PathVariable Integer tenantId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByTenant(tenantId);
        return ApiResponse.success(appointments);
    }

    /**
     * 获取房源的预约列表
     */
    @GetMapping("/house/{houseId}")
    public ApiResponse getHouseAppointments(@PathVariable Integer houseId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByHouse(houseId);
        return ApiResponse.success(appointments);
    }
}