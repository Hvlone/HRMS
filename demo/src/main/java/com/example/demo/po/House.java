package com.example.demo.po;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 房源信息实体类
 * 对应数据库表: house
 */
public class House {
    /**
     * 房源ID
     */
    private Integer houseId;

    /**
     * 房东ID
     */
    private Integer landlordId;

    /**
     * 房源标题
     */
    private String title;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 月租金
     */
    private int price;

    /**
     * 面积(㎡)
     */
    private Integer area;

    /**
     * 房源描述
     */
    private String description;

    /**
     * 状态：draft(草稿), pending_review(待审核), published(已发布), rejected(已拒绝), rented(已出租)
     */
    private String status;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    // 构造方法
    public House() {
    }

    public House(Integer houseId, Integer landlordId, String title, String address,
                 int price, Integer area, String description, String status,
                 LocalDateTime publishTime, LocalDateTime createTime) {
        this.houseId = houseId;
        this.landlordId = landlordId;
        this.title = title;
        this.address = address;
        this.price = price;
        this.area = area;
        this.description = description;
        this.status = status;
        this.publishTime = publishTime;
        this.createTime = createTime;
    }

    // Getter 和 Setter 方法
    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(Integer landlordId) {
        this.landlordId = landlordId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "House{" +
                "houseId=" + houseId +
                ", landlordId=" + landlordId +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", area=" + area +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", publishTime=" + publishTime +
                ", createTime=" + createTime +
                '}';
    }
}