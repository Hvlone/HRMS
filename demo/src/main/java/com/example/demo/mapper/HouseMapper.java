package com.example.demo.mapper;

import com.example.demo.po.House;
import org.apache.ibatis.annotations.*;

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
    @Insert("INSERT INTO house(landlord_id, title, address, price, area, description, status, create_time,update_time,pictures) " +
            "VALUES(#{landlordId}, #{title}, #{address}, #{price}, #{area}, #{description}, #{status}, #{createTime},#{updateTime},#{pictures})")
    @Options(useGeneratedKeys = true, keyProperty = "houseId")
    int insert(House house);

    /**
     * 更新房源状态
     * @param houseId 房源ID
     * @param status 新状态
     * @param publishTime 发布时间
     * @return 影响行数
     */
    @Update("UPDATE house SET status = #{status}, publish_time = #{publishTime} WHERE house_id = #{houseId}")
    int updateStatus(@Param("houseId") Integer houseId,
                     @Param("status") String status,
                     @Param("publishTime") Date publishTime);

    /**
     * 根据ID查询房源
     * @param houseId 房源ID
     * @return 房源实体
     */
    @Select("SELECT * FROM house WHERE house_id = #{houseId}")
    House selectById(Integer houseId);

    /**
     * 根据房东ID查询房源
     * @param landlordId 房东ID
     * @return 房源列表
     */
    @Select("SELECT * FROM house WHERE landlord_id = #{landlordId}")
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

    /*删除房源*/
    int deleteHouseByHouseId(Integer houseId);

    /*更新房源信息*/
    int updateHouse(House house);
}