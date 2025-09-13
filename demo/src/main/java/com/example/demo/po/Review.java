package com.example.demo.po;

import java.time.LocalDateTime;

/**
 * 房源评论实体类
 * 对应数据库表: review
 */
public class Review {
    private Integer reviewId;
    private Integer houseId;
    private Integer tenantId;
    private String content;
    private Integer rating; // 1-5
    private String status; // pending, approved, rejected
    private LocalDateTime createTime;

    // 构造方法
    public Review() {}

    public Review(Integer reviewId, Integer houseId, Integer tenantId,
                  String content, Integer rating, String status, LocalDateTime createTime) {
        this.reviewId = reviewId;
        this.houseId = houseId;
        this.tenantId = tenantId;
        this.content = content;
        this.rating = rating;
        this.status = status;
        this.createTime = createTime;
    }

    // Getter和Setter
    public Integer getReviewId() { return reviewId; }
    public void setReviewId(Integer reviewId) { this.reviewId = reviewId; }
    public Integer getHouseId() { return houseId; }
    public void setHouseId(Integer houseId) { this.houseId = houseId; }
    public Integer getTenantId() { return tenantId; }
    public void setTenantId(Integer tenantId) { this.tenantId = tenantId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }

    @Override
    public String toString() {
        return "Review{reviewId=" + reviewId + ", houseId=" + houseId +
                ", tenantId=" + tenantId + ", content='" + content +
                "', rating=" + rating + ", status='" + status +
                "', createTime=" + createTime + "}";
    }
}