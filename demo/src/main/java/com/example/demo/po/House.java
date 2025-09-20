package com.example.demo.po;

import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
public class House {

    private Integer houseId;
    private Integer landlordId;
    private String title;
    private String address;
    private BigDecimal price;
    private Integer area; // 面积
    private String description;
    private String status; // 状态：draft/pending_review/published/rejected/rented
    private Date publishTime;
    private Date createTime;

    public House() {}

    public House(Integer landlordId, String title, String address, BigDecimal price) {
        this.landlordId = landlordId;
        this.title = title;
        this.address = address;
        this.price = price;
        this.status = "draft";
        this.createTime = new Date();
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

    @Override
    public String toString() {
        return "House{" +
                "houseId=" + houseId +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public enum Status {
        DRAFT, PENDING_REVIEW, PUBLISHED, REJECTED, RENTED
    }
}
