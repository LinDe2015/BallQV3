package com.tysci.ballq.modles;

/**
 * Created by Administrator on 2016/4/27.
 */
public class BallQUserAchievementEntity {
    public int id;
    public int type;
    public String name;
    public String logo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
