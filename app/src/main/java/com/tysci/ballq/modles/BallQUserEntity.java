package com.tysci.ballq.modles;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
public class BallQUserEntity {
    private String className;
    private String portrait;
    private String firstName;
    private int userId;
    private int isAuthor;
    private int isV;
    private List<BallQUserAchievementEntity>achievements;

    public List<BallQUserAchievementEntity> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<BallQUserAchievementEntity> achievements) {
        this.achievements = achievements;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getIsAuthor() {
        return isAuthor;
    }

    public void setIsAuthor(int isAuthor) {
        this.isAuthor = isAuthor;
    }

    public int getIsV() {
        return isV;
    }

    public void setIsV(int isV) {
        this.isV = isV;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
