package com.example.demo.mapper;

import com.example.demo.po.House;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface HouseMapper {
    /**
     * 插入房源信息
     * @param house 房源实体
     * @return 影响行数
     */
    int insert(House house);

    /**
     * 更新房源状态
     * @param houseId 房源ID
     * @param status 新状态
     * @param publishTime 发布时间
     * @return 影响行数
     */
    int updateStatus(@Param("houseId") Integer houseId,
                     @Param("status") String status,
                     @Param("publishTime") Date publishTime);

    /**
     * 根据ID查询房源
     * @param houseId 房源ID
     * @return 房源实体
     */
    House selectById(Integer houseId);

    /**
     * 根据房东ID查询房源
     * @param landlordId 房东ID
     * @return 房源列表
     */
    List<House> selectByLandlordId(Integer landlordId);

    /**
     * 条件查询房源（分页）
     * @param minPrice 最低价格
     * @param maxPrice 最高价格
     * @param status 状态
     * @param keyword 关键词
     * @param offset 起始位置
     * @param limit 每页数量
     * @return 房源列表
     */
    List<House> selectByCondition(
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("status") String status,
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("limit") int limit);

    /**
     * 删除房源
     * @param houseId 房源ID
     * @return 影响行数
     */
    /*删除房源*/
    int deleteHouseByHouseId(Integer houseId);

    /**
     * 更新房源信息
     * @param house 房源实体
     * @return 影响行数
     */

    /*更新房源信息*/
    int updateHouse(House house);

    /*查询房东有多少个房源*/
    int selectHouseCount(int landlordId);

    //查找所有的房源
    List<House> selectAllHouse();
}