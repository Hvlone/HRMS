package com.example.demo.service;

import com.example.demo.po.House;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface HouseService {
    /**
     * 创建房源
     * @param house 房源信息
     * @return 创建结果
     */
    boolean createHouse(House house);

    /**
     * 更新房源状态
     * @param houseId 房源ID
     * @param status 新状态
     * @return 更新结果
     */
    boolean updateHouseStatus(Integer houseId, House.Status status);

    /**
     * 获取房源详情
     * @param houseId 房源ID
     * @return 房源详情
     */
    House getHouseDetail(Integer houseId);

    /**
     * 条件查询房源
     * @param minPrice 最低价格（元）
     * @param maxPrice 最高价格（元）
     * @param status 状态
     * @param keyword 关键词
     * @param page 页码
     * @param size 每页数量
     * @return 房源列表
     */
    List<House> searchHouses(BigDecimal minPrice, BigDecimal maxPrice,
                             House.Status status, String keyword,
                             int page, int size);

    /*删除房源*/
    int deleteHouseByHouseId(Integer houseId);

    /*更新房源信息*/
    int updateHouse(House house);

    /*查询房东有多少个房源*/
    int selectHouseCount(int landlordId);

    //查找所有的房源
    List<House> selectAllHouse();

    /*添加房源*/
    int addHouse(House house,@RequestParam("pics") MultipartFile[] pics) throws IOException;
}
