package com.my.bbs.entity;

import java.util.Date;

/**
 * 评论-实体类
 */
public class BBSPostComment {
    private Long commentId;

    private Long postId;

    private Long commentUserId;

    private String commentBody;

    private Long parentCommentUserId;

    private Date commentCreateTime;

    private Byte isDeleted;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Long commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody == null ? null : commentBody.trim();
    }

    public Long getParentCommentUserId() {
        return parentCommentUserId;
    }

    public void setParentCommentUserId(Long parentCommentUserId) {
        this.parentCommentUserId = parentCommentUserId;
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentId=").append(commentId);
        sb.append(", postId=").append(postId);
        sb.append(", commentUserId=").append(commentUserId);
        sb.append(", commentBody=").append(commentBody);
        sb.append(", parentCommentUserId=").append(parentCommentUserId);
        sb.append(", commentCreateTime=").append(commentCreateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append("]");
        return sb.toString();
    }
}