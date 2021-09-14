package com.my.bbs.entity;

import java.util.Date;

/**
 * 帖子-实体类
 */
public class BBSPost {
    private Long postId;

    private Long publishUserId;

    private String postTitle;

    private Integer postCategoryId;

    private String postCategoryName;

    private Byte postStatus;

    private Long postViews;

    private Long postComments;

    private Long postCollects;

    private Date lastUpdateTime;

    private Date createTime;

    private String postContent;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(Long publishUserId) {
        this.publishUserId = publishUserId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle == null ? null : postTitle.trim();
    }

    public Integer getPostCategoryId() {
        return postCategoryId;
    }

    public void setPostCategoryId(Integer postCategoryId) {
        this.postCategoryId = postCategoryId;
    }

    public String getPostCategoryName() {
        return postCategoryName;
    }

    public void setPostCategoryName(String postCategoryName) {
        this.postCategoryName = postCategoryName == null ? null : postCategoryName.trim();
    }

    public Byte getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Byte postStatus) {
        this.postStatus = postStatus;
    }

    public Long getPostViews() {
        return postViews;
    }

    public void setPostViews(Long postViews) {
        this.postViews = postViews;
    }

    public Long getPostComments() {
        return postComments;
    }

    public void setPostComments(Long postComments) {
        this.postComments = postComments;
    }

    public Long getPostCollects() {
        return postCollects;
    }

    public void setPostCollects(Long postCollects) {
        this.postCollects = postCollects;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent == null ? null : postContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", postId=").append(postId);
        sb.append(", publishUserId=").append(publishUserId);
        sb.append(", postTitle=").append(postTitle);
        sb.append(", postCategoryId=").append(postCategoryId);
        sb.append(", postCategoryName=").append(postCategoryName);
        sb.append(", postStatus=").append(postStatus);
        sb.append(", postViews=").append(postViews);
        sb.append(", postComments=").append(postComments);
        sb.append(", postCollects=").append(postCollects);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", postContent=").append(postContent);
        sb.append("]");
        return sb.toString();
    }
}