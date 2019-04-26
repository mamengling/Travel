package com.jcool.dev.travel.bean;

import com.google.gson.Gson;

public class TravelOrderInfoAdd {

    /**
     * id : 1118424009667551233
     * userId : 12
     * formCode : F1118424009487196162
     * state : CREATE
     * goodsId : 1101411899918745602
     * isSales : N
     * goodsDate : 1088717720311271426
     * goodsPrice : 2999
     * childrenNumber : 1
     * adultNumber : 1
     * goodsNumber : 2
     * totalMoney : 5998
     * payMoney : 5998
     * formDatetime : 1555488037832
     * linkMan : 李丽
     * linkPhone : 15554509193
     * linkMail : 15255@163.com
     * remarks : XP我好哦
     * people : 1116533023924363266,1116533179159748610,1117954817281564674,
     * payChannel : null
     * payOrderNumber : null
     */

    private String id;
    private String userId;
    private String formCode;
    private String state;
    private String goodsId;
    private String isSales;
    private String goodsDate;
    private int goodsPrice;
    private int childrenNumber;
    private int adultNumber;
    private int goodsNumber;
    private int totalMoney;
    private int payMoney;
    private long formDatetime;
    private String linkMan;
    private String linkPhone;
    private String linkMail;
    private String remarks;
    private String people;
    private Object payChannel;
    private Object payOrderNumber;

    public static TravelOrderInfoAdd objectFromData(String str) {

        return new Gson().fromJson(str, TravelOrderInfoAdd.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getIsSales() {
        return isSales;
    }

    public void setIsSales(String isSales) {
        this.isSales = isSales;
    }

    public String getGoodsDate() {
        return goodsDate;
    }

    public void setGoodsDate(String goodsDate) {
        this.goodsDate = goodsDate;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(int childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public int getAdultNumber() {
        return adultNumber;
    }

    public void setAdultNumber(int adultNumber) {
        this.adultNumber = adultNumber;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(int payMoney) {
        this.payMoney = payMoney;
    }

    public long getFormDatetime() {
        return formDatetime;
    }

    public void setFormDatetime(long formDatetime) {
        this.formDatetime = formDatetime;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getLinkMail() {
        return linkMail;
    }

    public void setLinkMail(String linkMail) {
        this.linkMail = linkMail;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public Object getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Object payChannel) {
        this.payChannel = payChannel;
    }

    public Object getPayOrderNumber() {
        return payOrderNumber;
    }

    public void setPayOrderNumber(Object payOrderNumber) {
        this.payOrderNumber = payOrderNumber;
    }
}
