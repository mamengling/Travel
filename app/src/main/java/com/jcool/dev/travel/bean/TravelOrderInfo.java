package com.jcool.dev.travel.bean;

import java.util.List;

public class TravelOrderInfo {

    /**
     * linkPhone : 18800000000
     * formCode : F1100935100197715970
     * goodsId : 1095588075600568321
     * isSales : N
     * totalMoney : 5998
     * childrenNumber : 1
     * userId : 7
     * adultNumber : 1
     * linkMan : 顶焦度计
     * people : [{"id":"1096251864909574145","userId":"1","custName":"我是专业测试11","custSex":"boy","custAge":"03","custType":"01","custCert":"111","delFlag":null,"createdTime":1550201787000,"updatedBy":null,"updatedTime":null,"certType":"ID"}]
     * formDatetime : 1551318357000
     * payMoney : 5998
     * goodsPrice : 2999
     * goodsNumber : 2
     * id : 1100935100214493186
     * state : REFUNDING
     * linkMail : hdhdh@66.com
     * goodsDate : 1095588084521852930
     * goodsName : 【全数据】日本东京+富士山+京都+大阪7日6晚半自助游(4钻)·品怀石泡温泉·9人团【新春大促】市区4星+箱根温泉·部分私汤【岚山小火车+新干线】蟹道乐+山梨特膳【B线赏樱专线·C线海陆空观富士山】千本鸟居·奈良逗鹿·2日自由
     * remarks : 不行你想不到
     */

    private String linkPhone;
    private String formCode;
    private String goodsId;
    private String isSales;
    private int totalMoney;
    private int childrenNumber;
    private String userId;
    private int adultNumber;
    private String linkMan;
    private long formDatetime;
    private int payMoney;
    private int goodsPrice;
    private int goodsNumber;
    private String id;
    private String state;
    private String linkMail;
    private String goodsDate;
    private String goodsName;
    private String remarks;
    private List<PeopleBean> people;

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
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

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(int childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAdultNumber() {
        return adultNumber;
    }

    public void setAdultNumber(int adultNumber) {
        this.adultNumber = adultNumber;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public long getFormDatetime() {
        return formDatetime;
    }

    public void setFormDatetime(long formDatetime) {
        this.formDatetime = formDatetime;
    }

    public int getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(int payMoney) {
        this.payMoney = payMoney;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLinkMail() {
        return linkMail;
    }

    public void setLinkMail(String linkMail) {
        this.linkMail = linkMail;
    }

    public String getGoodsDate() {
        return goodsDate;
    }

    public void setGoodsDate(String goodsDate) {
        this.goodsDate = goodsDate;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<PeopleBean> getPeople() {
        return people;
    }

    public void setPeople(List<PeopleBean> people) {
        this.people = people;
    }

    public static class PeopleBean {
        /**
         * id : 1096251864909574145
         * userId : 1
         * custName : 我是专业测试11
         * custSex : boy
         * custAge : 03
         * custType : 01
         * custCert : 111
         * delFlag : null
         * createdTime : 1550201787000
         * updatedBy : null
         * updatedTime : null
         * certType : ID
         */

        private String id;
        private String userId;
        private String custName;
        private String custSex;
        private String custAge;
        private String custType;
        private String custCert;
        private Object delFlag;
        private long createdTime;
        private Object updatedBy;
        private Object updatedTime;
        private String certType;

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

        public String getCustName() {
            return custName;
        }

        public void setCustName(String custName) {
            this.custName = custName;
        }

        public String getCustSex() {
            return custSex;
        }

        public void setCustSex(String custSex) {
            this.custSex = custSex;
        }

        public String getCustAge() {
            return custAge;
        }

        public void setCustAge(String custAge) {
            this.custAge = custAge;
        }

        public String getCustType() {
            return custType;
        }

        public void setCustType(String custType) {
            this.custType = custType;
        }

        public String getCustCert() {
            return custCert;
        }

        public void setCustCert(String custCert) {
            this.custCert = custCert;
        }

        public Object getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Object delFlag) {
            this.delFlag = delFlag;
        }

        public long getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(long createdTime) {
            this.createdTime = createdTime;
        }

        public Object getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(Object updatedBy) {
            this.updatedBy = updatedBy;
        }

        public Object getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(Object updatedTime) {
            this.updatedTime = updatedTime;
        }

        public String getCertType() {
            return certType;
        }

        public void setCertType(String certType) {
            this.certType = certType;
        }
    }
}
