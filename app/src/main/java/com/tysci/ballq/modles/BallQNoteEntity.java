package com.tysci.ballq.modles;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 * 表示帖子实体
 */
public class BallQNoteEntity {
    private int id;
    private int sectionId;
    private String title;
    private long createTime;
    private int clickCount;
    private int commentCount;
    private int viewCount;
    private int top;
    private int good;
    private int goodTop;
    private String shareUrl;
    private int isLike;
    private int isCollect;
    private int fid;
    private List<BallQNoteContentEntity> contents;
    private BallQUserEntity creater;

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public List<BallQNoteContentEntity> getContents() {
        return contents;
    }

    public void setContents(List<BallQNoteContentEntity> contents) {
        this.contents = contents;
    }

    public BallQUserEntity getCreater() {
        return creater;
    }

    public void setCreater(BallQUserEntity creater) {
        this.creater = creater;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getGoodTop() {
        return goodTop;
    }

    public void setGoodTop(int goodTop) {
        this.goodTop = goodTop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
