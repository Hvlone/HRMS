package com.example.demo.service.servceImpl;

import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.AppointmentMapper;
import com.example.demo.po.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentServiceImpl(AppointmentMapper appointmentMapper) {
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    @Transactional
    public boolean createAppointment(Appointment appointment) {
        if (appointment.getHouseId() == null) {
            throw new BusinessException("房源ID不能为空");
        }
        if (appointment.getTenantId() == null) {
            throw new BusinessException("租客ID不能为空");
        }
        if (appointment.getAppointTime() == null) {
            throw new BusinessException("预约时间不能为空");
        }
        
        // 设置默认状态和创建时间
        if (appointment.getStatus() == null) {
            appointment.setStatus(Appointment.Status.PENDING);
        }
        if (appointment.getCreateTime() == null) {
            appointment.setCreateTime(new Date());
        }
        
        return appointmentMapper.insert(appointment) > 0;
    }

    @Override
    @Transactional
    public boolean updateAppointmentStatus(Integer appointId, Appointment.Status status) {
        // 验证预约是否存在
        Appointment existing = appointmentMapper.selectById(appointId);
        if (existing == null) {
            throw new BusinessException("预约记录不存在");
        }
        
        // 状态转换校验
        if (existing.getStatus() == Appointment.Status.CANCELED) {
            throw new BusinessException("已取消的预约不能修改状态");
        }
        
        return appointmentMapper.updateStatus(appointId, status) > 0;
    }

    @Override
    public Appointment getAppointmentDetail(Integer appointId) {
        return appointmentMapper.selectById(appointId);
    }

    @Override
    public List<Appointment> getAppointmentsByTenant(Integer tenantId) {
        return appointmentMapper.selectByTenantId(tenantId);
    }

    @Override
    public List<Appointment> getAppointmentsByHouse(Integer houseId) {
        return appointmentMapper.selectByHouseId(houseId);
    }
}