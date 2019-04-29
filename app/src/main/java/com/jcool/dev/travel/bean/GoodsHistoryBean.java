package com.jcool.dev.travel.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;

public class GoodsHistoryBean {
    /**
     * 唯一标识
     */
    @Id(column = "id")
    private String id;
    /**
     *
     */
    @Column(column = "type")
    private String type;
    @Column(column = "name")
    private String name;
    @Column(column = "image")
    private String image;
    @Column(column = "money")
    private String money;
    @Column(column = "days")
    private String days;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
