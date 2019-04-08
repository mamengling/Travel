package com.jcool.dev.travel.bean;

public class BannerBean {

    /**
     * id : 5
     * name : null
     * picUrl : http://cyitsapp.oss-cn-beijing.aliyuncs.com/434e487e18254562b88e37dd6aa6a0b1.jpg
     * productId : null
     * sort : 0
     */

    private int id;
    private String name;
    private String picUrl;
    private String productId;
    private int sort;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
