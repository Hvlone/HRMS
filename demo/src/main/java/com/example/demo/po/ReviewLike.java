package com.example.demo.po;

import java.time.LocalDateTime;

/**
 * 评论点赞实体类
 * 对应数据库表: review_like
 */
public class ReviewLike {
    private Integer likeId;
    private Integer reviewId;
    private Integer userId;
    private LocalDateTime likeTime;

    // 构造方法
    public ReviewLike() {}

    public ReviewLike(Integer likeId, Integer reviewId, Integer userId, LocalDateTime likeTime) {
        this.likeId = likeId;
        this.reviewId = reviewId;
        this.userId = userId;
        this.likeTime = likeTime;
    }

    // Getter和Setter
    public Integer getLikeId() { return likeId; }
    public void setLikeId(Integer likeId) { this.likeId = likeId; }
    public Integer getReviewId() { return reviewId; }
    public void setReviewId(Integer reviewId) { this.reviewId = reviewId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public LocalDateTime getLikeTime() { return likeTime; }
    public void setLikeTime(LocalDateTime likeTime) { this.likeTime = likeTime; }

    @Override
    public String toString() {
        return "ReviewLike{likeId=" + likeId + ", reviewId=" + reviewId +
                ", userId=" + userId + ", likeTime=" + likeTime + "}";
    }
}