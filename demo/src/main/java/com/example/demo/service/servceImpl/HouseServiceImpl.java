package com.example.demo.service.servceImpl;

import com.example.demo.service.HouseService;

import com.example.demo.po.House;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.HouseMapper;
import com.example.demo.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    private final HouseMapper houseMapper;

    @Autowired
    public HouseServiceImpl(HouseMapper houseMapper) {
        this.houseMapper = houseMapper;
    }

    @Override
    @Transactional
    public boolean createHouse(House house) {
        // 验证房东是否存在
        if (house.getLandlordId() == null) {
            throw new BusinessException("房东ID不能为空");
        }
        
        // 设置默认状态
        if (house.getStatus() == null) {
            house.setStatus(House.Status.DRAFT.name());
        }
        
        return houseMapper.insert(house) > 0;
    }

    @Override
    @Transactional
    public boolean updateHouseStatus(Integer houseId, House.Status status) {
        House house = houseMapper.selectById(houseId);
        if (house == null) {
            throw new BusinessException("房源不存在");
        }
        
        // 状态转换校验
        if (status == House.Status.PUBLISHED && !House.Status.PENDING_REVIEW.name().equals(house.getStatus())) {
            throw new BusinessException("只有待审核状态的房源可以发布");
        }
        
        // 设置发布时间
        Date publishTime = null;
        if (status == House.Status.PUBLISHED) {
            publishTime = new Date();
        }
        
        return houseMapper.updateStatus(houseId, status.name(), publishTime) > 0;
    }

    @Override
    public House getHouseDetail(Integer houseId) {
        return houseMapper.selectById(houseId);
    }

    @Override
    public List<House> searchHouses(BigDecimal minPrice, BigDecimal maxPrice,
                                    House.Status status, String keyword,
                                    int page, int size) {
        // 分页参数转换
        int offset = (page - 1) * size;


        // 状态转换
        String statusStr = status != null ? status.name() : null;
        
        return houseMapper.selectByCondition(
            minPrice, maxPrice, statusStr, keyword, offset, size);
    }
}
