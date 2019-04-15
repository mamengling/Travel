package com.jcool.dev.travel.bean;

import com.google.gson.Gson;

import java.util.List;

public class OrderVisaInfo {

    /**
     * records : [{"id":"1098397292694310914","visaId":"1098157975933071362","visaPlace":null,"visaName":null,"visaImage":null,"visaPrice":null,"visaTotalamt":null,"peopleCount":null,"linkName":"基督教","linkPhone":"18800000000","linkEmail":"jjjd@77.com","custIds":"1096251864909574145,1098393023064805377","tripTime":"2019-02-24","dataCommitTime":"2019-05-21","giveType":"02","orderStatus":"10","commitUserId":"7","commitTime":1550713296000,"detailAddress":null,"expressCompany":null,"expressNo":null,"specName":null,"specIndex":null,"specPrice":null,"refundTime":1551139798000,"payChannel":"01"},{"id":"1098398894536757250","visaId":"1098157975933071362","visaPlace":null,"visaName":null,"visaImage":null,"visaPrice":null,"visaTotalamt":null,"peopleCount":null,"linkName":"度假酒店","linkPhone":"18800000000","linkEmail":"jdjdjd@cc.com","custIds":"1096251864909574145,1098393023064805377","tripTime":"2019-02-21","dataCommitTime":"2019-05-21","giveType":"02","orderStatus":"10","commitUserId":"7","commitTime":1550713678000,"detailAddress":null,"expressCompany":null,"expressNo":null,"specName":null,"specIndex":null,"specPrice":null,"refundTime":1551139799000,"payChannel":"1"},{"id":"1098398948769107970","visaId":"1098157975933071362","visaPlace":null,"visaName":null,"visaImage":null,"visaPrice":null,"visaTotalamt":null,"peopleCount":null,"linkName":"度假酒店","linkPhone":"18800000000","linkEmail":"jdjdjd@cc.com","custIds":"1096251864909574145,1098393023064805377","tripTime":"2019-02-21","dataCommitTime":"2019-05-21","giveType":"02","orderStatus":"10","commitUserId":"7","commitTime":1550713691000,"detailAddress":null,"expressCompany":null,"expressNo":null,"specName":null,"specIndex":null,"specPrice":null,"refundTime":1551139802000,"payChannel":"1"},{"id":"1098398960664154113","visaId":"1098157975933071362","visaPlace":null,"visaName":null,"visaImage":null,"visaPrice":null,"visaTotalamt":null,"peopleCount":null,"linkName":"度假酒店","linkPhone":"18800000000","linkEmail":"jdjdjd@cc.com","custIds":"1096251864909574145,1098393023064805377","tripTime":"2019-02-21","dataCommitTime":"2019-05-21","giveType":"02","orderStatus":"10","commitUserId":"7","commitTime":1550713694000,"detailAddress":null,"expressCompany":null,"expressNo":null,"specName":null,"specIndex":null,"specPrice":null,"refundTime":1551139803000,"payChannel":"01"},{"id":"1098399147939827713","visaId":"1098157975933071362","visaPlace":null,"visaName":null,"visaImage":null,"visaPrice":null,"visaTotalamt":null,"peopleCount":null,"linkName":"顶焦度计","linkPhone":"4888446","linkEmail":"xjdjjd@jj.com","custIds":"1096251864909574145","tripTime":"2019-02-21","dataCommitTime":"2019-04-21","giveType":"02","orderStatus":"01","commitUserId":"7","commitTime":1550713739000,"detailAddress":null,"expressCompany":null,"expressNo":null,"specName":null,"specIndex":null,"specPrice":null,"refundTime":1551139803000,"payChannel":"01"},{"id":"1100997136147513346","visaId":"1098157975933071362","visaPlace":"东京","visaName":"签证产品二","visaImage":"http://jcools.oss-cn-qingdao.aliyuncs.com/1231c74be81b4378be51609c41ae4110.png","visaPrice":100,"visaTotalamt":200,"peopleCount":2,"linkName":"土坷垃","linkPhone":"16600000000","linkEmail":"jdjdh@yy.com","custIds":"1096251864909574145,1098393023064805377","tripTime":"2020-02-28","dataCommitTime":"2019-02-28","giveType":"02","orderStatus":"08","commitUserId":"7","commitTime":1551333147000,"detailAddress":null,"expressCompany":null,"expressNo":null,"specName":"规格一","specIndex":"0","specPrice":"100.00","refundTime":1551333470000,"payChannel":null},{"id":"1101001556709048322","visaId":"1098157975933071362","visaPlace":"东京","visaName":"签证产品二","visaImage":"http://jcools.oss-cn-qingdao.aliyuncs.com/1231c74be81b4378be51609c41ae4110.png","visaPrice":100,"visaTotalamt":200,"peopleCount":2,"linkName":"刘德华","linkPhone":"10000000000","linkEmail":"jdhd@6.com","custIds":"1096251864909574145,1098393023064805377","tripTime":"2019-04-28","dataCommitTime":"2019-07-28","giveType":"02","orderStatus":"01","commitUserId":"7","commitTime":1551334201000,"detailAddress":null,"expressCompany":null,"expressNo":null,"specName":"规格一","specIndex":"0","specPrice":"100.00","refundTime":null,"payChannel":null},{"id":"1101002482433884162","visaId":"1098158267378479105","visaPlace":"东京","visaName":"签证产品八","visaImage":"http://jcools.oss-cn-qingdao.aliyuncs.com/1231c74be81b4378be51609c41ae4110.png","visaPrice":50,"visaTotalamt":200,"peopleCount":2,"linkName":"范冰冰","linkPhone":"15500000000","linkEmail":"hdhdh@yy.com","custIds":"1096251864909574145,1098393023064805377","tripTime":"2019-04-28","dataCommitTime":"2019-07-28","giveType":"02","orderStatus":"01","commitUserId":"7","commitTime":1551334422000,"detailAddress":null,"expressCompany":null,"expressNo":null,"specName":"规格一","specIndex":"0","specPrice":"100.00","refundTime":null,"payChannel":null},{"id":"1101002968486608898","visaId":"1098158267378479105","visaPlace":"东京","visaName":"签证产品八","visaImage":"http://jcools.oss-cn-qingdao.aliyuncs.com/1231c74be81b4378be51609c41ae4110.png","visaPrice":50,"visaTotalamt":100,"peopleCount":2,"linkName":"范冰冰","linkPhone":"15500000000","linkEmail":"hdhfh@yyy.com","custIds":"1096251864909574145,1098393023064805377","tripTime":"2019-02-28","dataCommitTime":"2019-06-28","giveType":"02","orderStatus":"10","commitUserId":"7","commitTime":1551334538000,"detailAddress":null,"expressCompany":null,"expressNo":null,"specName":"规格四","specIndex":"3","specPrice":"50.00","refundTime":1551386284000,"payChannel":null},{"id":"1101003449300647937","visaId":"1098158268385112065","visaPlace":"东京","visaName":"签证产品九","visaImage":"http://jcools.oss-cn-qingdao.aliyuncs.com/1231c74be81b4378be51609c41ae4110.png","visaPrice":50,"visaTotalamt":160,"peopleCount":2,"linkName":"红尘繁华","linkPhone":"18800000000","linkEmail":"fhhfh@yy.com","custIds":"1096251864909574145,1098393023064805377","tripTime":"2019-03-15","dataCommitTime":"2019-09-28","giveType":"02","orderStatus":"13","commitUserId":"7","commitTime":1551334653000,"detailAddress":null,"expressCompany":null,"expressNo":null,"specName":"规格三","specIndex":"2","specPrice":"80.00","refundTime":1551389390000,"payChannel":null}]
     * total : 14
     * size : 10
     * current : 1
     * searchCount : true
     * pages : 2
     */

    private int total;
    private String size;
    private String current;
    private boolean searchCount;
    private String pages;
    private List<RecordsBean> records;

    public static OrderVisaInfo objectFromData(String str) {

        return new Gson().fromJson(str, OrderVisaInfo.class);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * id : 1098397292694310914
         * visaId : 1098157975933071362
         * visaPlace : null
         * visaName : null
         * visaImage : null
         * visaPrice : null
         * visaTotalamt : null
         * peopleCount : null
         * linkName : 基督教
         * linkPhone : 18800000000
         * linkEmail : jjjd@77.com
         * custIds : 1096251864909574145,1098393023064805377
         * tripTime : 2019-02-24
         * dataCommitTime : 2019-05-21
         * giveType : 02
         * orderStatus : 10
         * commitUserId : 7
         * commitTime : 1550713296000
         * detailAddress : null
         * expressCompany : null
         * expressNo : null
         * specName : null
         * specIndex : null
         * specPrice : null
         * refundTime : 1551139798000
         * payChannel : 01
         */

        private String id;
        private String visaId;
        private String visaPlace;
        private String visaName;
        private String visaImage;
        private String visaPrice;
        private String visaTotalamt;
        private String peopleCount;
        private String linkName;
        private String linkPhone;
        private String linkEmail;
        private String custIds;
        private String tripTime;
        private String dataCommitTime;
        private String giveType;
        private String orderStatus;
        private String commitUserId;
        private long commitTime;
        private String detailAddress;
        private String expressCompany;
        private String expressNo;
        private String specName;
        private String specIndex;
        private String specPrice;
        private long refundTime;
        private String payChannel;

        public static RecordsBean objectFromData(String str) {

            return new Gson().fromJson(str, RecordsBean.class);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVisaId() {
            return visaId;
        }

        public void setVisaId(String visaId) {
            this.visaId = visaId;
        }

        public String getVisaPlace() {
            return visaPlace;
        }

        public void setVisaPlace(String visaPlace) {
            this.visaPlace = visaPlace;
        }

        public String getVisaName() {
            return visaName;
        }

        public void setVisaName(String visaName) {
            this.visaName = visaName;
        }

        public String getVisaImage() {
            return visaImage;
        }

        public void setVisaImage(String visaImage) {
            this.visaImage = visaImage;
        }

        public String getVisaPrice() {
            return visaPrice;
        }

        public void setVisaPrice(String visaPrice) {
            this.visaPrice = visaPrice;
        }

        public String getVisaTotalamt() {
            return visaTotalamt;
        }

        public void setVisaTotalamt(String visaTotalamt) {
            this.visaTotalamt = visaTotalamt;
        }

        public String getPeopleCount() {
            return peopleCount;
        }

        public void setPeopleCount(String peopleCount) {
            this.peopleCount = peopleCount;
        }

        public String getLinkName() {
            return linkName;
        }

        public void setLinkName(String linkName) {
            this.linkName = linkName;
        }

        public String getLinkPhone() {
            return linkPhone;
        }

        public void setLinkPhone(String linkPhone) {
            this.linkPhone = linkPhone;
        }

        public String getLinkEmail() {
            return linkEmail;
        }

        public void setLinkEmail(String linkEmail) {
            this.linkEmail = linkEmail;
        }

        public String getCustIds() {
            return custIds;
        }

        public void setCustIds(String custIds) {
            this.custIds = custIds;
        }

        public String getTripTime() {
            return tripTime;
        }

        public void setTripTime(String tripTime) {
            this.tripTime = tripTime;
        }

        public String getDataCommitTime() {
            return dataCommitTime;
        }

        public void setDataCommitTime(String dataCommitTime) {
            this.dataCommitTime = dataCommitTime;
        }

        public String getGiveType() {
            return giveType;
        }

        public void setGiveType(String giveType) {
            this.giveType = giveType;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getCommitUserId() {
            return commitUserId;
        }

        public void setCommitUserId(String commitUserId) {
            this.commitUserId = commitUserId;
        }

        public long getCommitTime() {
            return commitTime;
        }

        public void setCommitTime(long commitTime) {
            this.commitTime = commitTime;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
        }

        public String getExpressCompany() {
            return expressCompany;
        }

        public void setExpressCompany(String expressCompany) {
            this.expressCompany = expressCompany;
        }

        public String getExpressNo() {
            return expressNo;
        }

        public void setExpressNo(String expressNo) {
            this.expressNo = expressNo;
        }

        public String getSpecName() {
            return specName;
        }

        public void setSpecName(String specName) {
            this.specName = specName;
        }

        public String getSpecIndex() {
            return specIndex;
        }

        public void setSpecIndex(String specIndex) {
            this.specIndex = specIndex;
        }

        public String getSpecPrice() {
            return specPrice;
        }

        public void setSpecPrice(String specPrice) {
            this.specPrice = specPrice;
        }

        public long getRefundTime() {
            return refundTime;
        }

        public void setRefundTime(long refundTime) {
            this.refundTime = refundTime;
        }

        public String getPayChannel() {
            return payChannel;
        }

        public void setPayChannel(String payChannel) {
            this.payChannel = payChannel;
        }
    }
}
