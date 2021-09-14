package com.my.bbs.entity;

import java.util.Date;

/**
 * 个人中心最近评论列表-实体类
 * 页面展示时需要的字段与评论实体类不同，因此新增了这个类
 */
public class RecentCommentListEntity {

    private Long postId;

    private String postTitle;

    private String commentBody;

    private Date commentCreateTime;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }
}