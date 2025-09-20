package com.example.demo.po;

import java.time.LocalDateTime;

/**
 * 维修评价实体类
 * 对应数据库表: repair_review
 */
public class RepairReview {
    private Integer repairReviewId;
    private Integer repairId;
    private Integer tenantId;
    private Integer rating; // 1-5
    private String comment;
    private LocalDateTime reviewTime;

    // 构造方法
    public RepairReview() {}

    public RepairReview(Integer repairReviewId, Integer repairId, Integer tenantId,
                        Integer rating, String comment, LocalDateTime reviewTime) {
        this.repairReviewId = repairReviewId;
        this.repairId = repairId;
        this.tenantId = tenantId;
        this.rating = rating;
        this.comment = comment;
        this.reviewTime = reviewTime;
    }

    // Getter和Setter
    public Integer getRepairReviewId() { return repairReviewId; }
    public void setRepairReviewId(Integer repairReviewId) { this.repairReviewId = repairReviewId; }
    public Integer getRepairId() { return repairId; }
    public void setRepairId(Integer repairId) { this.repairId = repairId; }
    public Integer getTenantId() { return tenantId; }
    public void setTenantId(Integer tenantId) { this.tenantId = tenantId; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public LocalDateTime getReviewTime() { return reviewTime; }
    public void setReviewTime(LocalDateTime reviewTime) { this.reviewTime = reviewTime; }

    @Override
    public String toString() {
        return "RepairReview{repairReviewId=" + repairReviewId + ", repairId=" + repairId +
                ", tenantId=" + tenantId + ", rating=" + rating +
                ", comment='" + comment + "', reviewTime=" + reviewTime + "}";
    }
}