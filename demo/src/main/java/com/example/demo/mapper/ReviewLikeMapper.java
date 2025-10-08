package com.example.demo.mapper;

import com.example.demo.po.ReviewLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ReviewLikeMapper {

    /* 新增并返回主键 */
    int insert(ReviewLike record);

    /* 根据主键查询 */
    ReviewLike selectByPrimaryKey(Integer likeId);

    /* 查询全部 */
    List<ReviewLike> selectAll();

    /* 根据评论ID查询点赞列表 */
    List<ReviewLike> selectByReviewId(Integer reviewId);

    /* 根据用户ID查询点赞列表 */
    List<ReviewLike> selectByUserId(Integer userId);

    /* 判断某用户是否已对某评论点赞（防重复） */
    ReviewLike selectByReviewIdAndUserId(Integer reviewId, Integer userId);

    /* 动态更新 */
    int updateByPrimaryKeySelective(ReviewLike record);

    /* 根据主键删除 */
    int deleteByPrimaryKey(Integer likeId);

    /* 取消点赞：根据 reviewId + userId 删除 */
    int deleteByReviewIdAndUserId(Integer reviewId, Integer userId);
}