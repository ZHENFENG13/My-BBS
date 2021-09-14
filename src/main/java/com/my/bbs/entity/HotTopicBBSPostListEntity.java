package com.my.bbs.entity;

/**
 * 近期热议帖子列表-实体类
 * 页面展示时需要的字段仅需要id、标题、评论数三个字段，因此新增了这个类
 */
public class HotTopicBBSPostListEntity {
    private Long postId;

    private String postTitle;

    private Long postComments;

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

    public Long getPostComments() {
        return postComments;
    }

    public void setPostComments(Long postComments) {
        this.postComments = postComments;
    }

    @Override
    public String toString() {
        return "HotTopicBBSPostListEntity{" +
                "postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", postComments=" + postComments +
                '}';
    }
}