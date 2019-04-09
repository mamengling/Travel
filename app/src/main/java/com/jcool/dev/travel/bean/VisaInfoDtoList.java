package com.jcool.dev.travel.bean;

import java.util.List;

public class VisaInfoDtoList {

    /**
     * id : 1
     * visaSortName : 亚洲热门
     * visaInfoDtoList : [{"id":"1109640850598969346","visaName":"韩国签证加急个人旅游多次往返","visaImage":"http://cyitsapp.oss-cn-beijing.aliyuncs.com/fe759ddad06b44c88858e7ab35aa99bc.jpg","visaPrice":"348.00","stayDays":null}]
     */

    private int viewType;
    private String id;
    private String visaSortName;
    private VisaInfoDtoListBean content;
    private List<VisaInfoDtoListBean> visaInfoDtoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisaSortName() {
        return visaSortName;
    }

    public void setVisaSortName(String visaSortName) {
        this.visaSortName = visaSortName;
    }

    public List<VisaInfoDtoListBean> getVisaInfoDtoList() {
        return visaInfoDtoList;
    }

    public void setVisaInfoDtoList(List<VisaInfoDtoListBean> visaInfoDtoList) {
        this.visaInfoDtoList = visaInfoDtoList;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public VisaInfoDtoListBean getContent() {
        return content;
    }

    public void setContent(VisaInfoDtoListBean content) {
        this.content = content;
    }

    public static class VisaInfoDtoListBean {
        /**
         * id : 1109640850598969346
         * visaName : 韩国签证加急个人旅游多次往返
         * visaImage : http://cyitsapp.oss-cn-beijing.aliyuncs.com/fe759ddad06b44c88858e7ab35aa99bc.jpg
         * visaPrice : 348.00
         * stayDays : null
         */
        private int viewType;
        private String id;
        private String visaName;
        private String visaSortName;
        private String visaImage;
        private String visaPrice;
        private Object stayDays;

        public String getVisaSortName() {
            return visaSortName;
        }

        public void setVisaSortName(String visaSortName) {
            this.visaSortName = visaSortName;
        }

        public int getViewType() {
            return viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public Object getStayDays() {
            return stayDays;
        }

        public void setStayDays(Object stayDays) {
            this.stayDays = stayDays;
        }
    }
}
