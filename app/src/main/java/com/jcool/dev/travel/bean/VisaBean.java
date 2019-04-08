package com.jcool.dev.travel.bean;

import java.util.List;

public class VisaBean {
    /**
     * records : [{"id":"1109640850598969346","visaName":"韩国签证加急个人旅游多次往返","visaImage":"http://cyitsapp.oss-cn-beijing.aliyuncs.com/fe759ddad06b44c88858e7ab35aa99bc.jpg","visaPrice":"348.00","stayDays":"30"},{"id":"1109646941131030530","visaName":"日本签证个人旅游自由行商务","visaImage":"http://cyitsapp.oss-cn-beijing.aliyuncs.com/9124bd383fc24d3b943eb73d0c26032e.jpg","visaPrice":"65.00","stayDays":"15"},{"id":"1109667919647129602","visaName":"泰国","visaImage":"http://cyitsapp.oss-cn-beijing.aliyuncs.com/1685e262ecaa4583ab6f9d24cc16178f.jpg","visaPrice":"275.00","stayDays":"59"}]
     * total : 3
     * size : 10
     * current : 1
     * searchCount : true
     * pages : 1
     */

    private int total;
    private int size;
    private String current;
    private boolean searchCount;
    private String pages;
    private List<RecordsBean> records;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
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
         * id : 1109640850598969346
         * visaName : 韩国签证加急个人旅游多次往返
         * visaImage : http://cyitsapp.oss-cn-beijing.aliyuncs.com/fe759ddad06b44c88858e7ab35aa99bc.jpg
         * visaPrice : 348.00
         * stayDays : 30
         */

        private String id;
        private String visaName;
        private String visaImage;
        private String visaPrice;
        private String stayDays;

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

        public String getStayDays() {
            return stayDays;
        }

        public void setStayDays(String stayDays) {
            this.stayDays = stayDays;
        }
    }
}
