package com.jcool.dev.travel.bean;

import java.util.ArrayList;
import java.util.List;

public class VisaOrderInfo {

    /**
     * id : 1116231739908308993
     * visaId : 1098157663159627777
     * validityDate : 30
     * entryCount : 2
     * stayDays : 90
     * visaPlace : null
     * visaName : 签证产品一
     * visaImage : null
     * visaPrice : null
     * visaTotalamt : null
     * peopleCount : 1
     * linkName : 马梦玲
     * linkPhone : null
     * linkEmail : null
     * custIds : 1116214240177307649,
     * tripTime : null
     * dataCommitTime : null
     * giveType : null
     * orderStatus : 03
     * commitUserId : null
     * commitTime : null
     * detailAddress : null
     * expressCompany : null
     * expressNo : null
     * workingData : [{"name":"身份证","content":"身份证信息","index":0,"type":0,"initRowIndex":0}]
     * freeData : [{"name":"身份证","content":"身份描述","index":1,"type":1,"initRowIndex":1}]
     * studentData : []
     * retireData : []
     * babyData : []
     * customer : [{"id":"1116214240177307649","userId":"16","custName":"马梦玲","custSex":"girl","custAge":"03","custType":"01","custCert":"372928199107241026","certType":"ID","data":"[{\"name\":\"身份证\",\"content\":\"身份证信息\",\"index\":0,\"type\":0,\"initRowIndex\":0}]","visaOrderData":[]}]
     */

    private String id;
    private String visaId;
    private String validityDate;
    private int entryCount;
    private int stayDays;
    private Object visaPlace;
    private String visaName;
    private Object visaImage;
    private Object visaPrice;
    private Object visaTotalamt;
    private int peopleCount;
    private String linkName;
    private String linkPhone;
    private String linkEmail;
    private String custIds;
    private Object tripTime;
    private Object dataCommitTime;
    private Object giveType;
    private String orderStatus;
    private Object commitUserId;
    private Object commitTime;
    private Object detailAddress;
    private Object expressCompany;
    private Object expressNo;
    private String workingData;
    private String freeData;
    private String studentData;
    private String retireData;
    private String babyData;
    private List<CustomerBean> customer;

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

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }

    public int getStayDays() {
        return stayDays;
    }

    public void setStayDays(int stayDays) {
        this.stayDays = stayDays;
    }

    public Object getVisaPlace() {
        return visaPlace;
    }

    public void setVisaPlace(Object visaPlace) {
        this.visaPlace = visaPlace;
    }

    public String getVisaName() {
        return visaName;
    }

    public void setVisaName(String visaName) {
        this.visaName = visaName;
    }

    public Object getVisaImage() {
        return visaImage;
    }

    public void setVisaImage(Object visaImage) {
        this.visaImage = visaImage;
    }

    public Object getVisaPrice() {
        return visaPrice;
    }

    public void setVisaPrice(Object visaPrice) {
        this.visaPrice = visaPrice;
    }

    public Object getVisaTotalamt() {
        return visaTotalamt;
    }

    public void setVisaTotalamt(Object visaTotalamt) {
        this.visaTotalamt = visaTotalamt;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
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

    public Object getTripTime() {
        return tripTime;
    }

    public void setTripTime(Object tripTime) {
        this.tripTime = tripTime;
    }

    public Object getDataCommitTime() {
        return dataCommitTime;
    }

    public void setDataCommitTime(Object dataCommitTime) {
        this.dataCommitTime = dataCommitTime;
    }

    public Object getGiveType() {
        return giveType;
    }

    public void setGiveType(Object giveType) {
        this.giveType = giveType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Object getCommitUserId() {
        return commitUserId;
    }

    public void setCommitUserId(Object commitUserId) {
        this.commitUserId = commitUserId;
    }

    public Object getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Object commitTime) {
        this.commitTime = commitTime;
    }

    public Object getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(Object detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Object getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(Object expressCompany) {
        this.expressCompany = expressCompany;
    }

    public Object getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(Object expressNo) {
        this.expressNo = expressNo;
    }

    public String getWorkingData() {
        return workingData;
    }

    public void setWorkingData(String workingData) {
        this.workingData = workingData;
    }

    public String getFreeData() {
        return freeData;
    }

    public void setFreeData(String freeData) {
        this.freeData = freeData;
    }

    public String getStudentData() {
        return studentData;
    }

    public void setStudentData(String studentData) {
        this.studentData = studentData;
    }

    public String getRetireData() {
        return retireData;
    }

    public void setRetireData(String retireData) {
        this.retireData = retireData;
    }

    public String getBabyData() {
        return babyData;
    }

    public void setBabyData(String babyData) {
        this.babyData = babyData;
    }

    public List<CustomerBean> getCustomer() {
        return customer;
    }

    public void setCustomer(List<CustomerBean> customer) {
        this.customer = customer;
    }

    public static class CustomerBean {
        /**
         * id : 1116214240177307649
         * userId : 16
         * custName : 马梦玲
         * custSex : girl
         * custAge : 03
         * custType : 01
         * custCert : 372928199107241026
         * certType : ID
         * data : [{"name":"身份证","content":"身份证信息","index":0,"type":0,"initRowIndex":0}]
         * visaOrderData : []
         */

        private String id;
        private String userId;
        private String custName;
        private String custSex;
        private String custAge;
        private String custType;
        private String custCert;
        private String certType;
        private String data;
        private ArrayList<GroupBean> dataList;
        private List<DataImageInfo> visaOrderData;

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

        public String getCertType() {
            return certType;
        }

        public void setCertType(String certType) {
            this.certType = certType;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public ArrayList<GroupBean> getDataList() {
            return dataList;
        }

        public void setDataList(ArrayList<GroupBean> dataList) {
            this.dataList = dataList;
        }

        public List<DataImageInfo> getVisaOrderData() {
            return visaOrderData;
        }

        public void setVisaOrderData(List<DataImageInfo> visaOrderData) {
            this.visaOrderData = visaOrderData;
        }
    }
}
