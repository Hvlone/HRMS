package com.example.demo.po;

import com.example.demo.common.MessageConstant;

import java.math.BigDecimal;
import java.util.Date;


public class House {

    private Integer houseId;
    private Integer landlordId;
    private String title;
    private String address;
    private BigDecimal price;
    private Integer area; // 面积
    private String description;
    private String status; // 状态：draft/pending_review/published/rejected/rented
    private Date publishTime;//发布时间
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private String houseFacility;//房子的配套设施
    private String houseFloor;
    private String houseType;
    private String houseOrientation;
    // 只存“相对路径”，用逗号分隔多图
    private String pictures;
    public House() {}

    public House(String houseFloor, Integer houseId, Integer landlordId, String title, String address, BigDecimal price, Integer area, String description, String status, Date publishTime, Date createTime, Date updateTime, String houseFacility, String houseType, String houseOrientation, String pictures) {
        this.houseFloor = houseFloor;
        this.title = title;
        this.address = address;
        this.price = price;
        this.area = area;
        this.description = description;
        this.status = MessageConstant.PENDING_REVIEW;
        this.houseFacility = houseFacility;
        this.houseType = houseType;
        this.houseOrientation = houseOrientation;
        this.pictures = pictures;
    }

    public String getHouseFacility() {
        return houseFacility;
    }

    public void setHouseFacility(String houseFacility) {
        this.houseFacility = houseFacility;
    }

    public String getHouseFloor() {
        return houseFloor;
    }

    public void setHouseFloor(String houseFloor) {
        this.houseFloor = houseFloor;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseOrientation() {
        return houseOrientation;
    }

    public void setHouseOrientation(String houseOrientation) {
        this.houseOrientation = houseOrientation;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
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
                ", updateTime=" + updateTime +
                ", houseFacility='" + houseFacility + '\'' +
                ", houseFloor='" + houseFloor + '\'' +
                ", houseType='" + houseType + '\'' +
                ", houseOrientation='" + houseOrientation + '\'' +
                ", pictures='" + pictures + '\'' +
                '}';
    }

    public enum Status {
        DRAFT, PENDING_REVIEW, PUBLISHED, REJECTED, RENTED
    }
}
