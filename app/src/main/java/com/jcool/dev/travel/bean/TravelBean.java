package com.jcool.dev.travel.bean;

import java.util.List;

public class TravelBean {

    /**
     * records : [{"id":"1110007634489425921","goodsCode":"GOODS1110007634485231618","onLine":"Y","isGroup":null,"isDomestic":"N","name":"触摸印度","defaultSellNumber":100,"actualSellNumber":0,"whereFrom":null,"whereTo":null,"visa":null,"tags":null,"aroundCity":null,"content":"触摸印度","sortIndex":1,"headImg":"http://cyitsapp.oss-cn-beijing.aliyuncs.com/443c9097f95f4ecfb0a5e3f8c0e6b3a2.jpg","days":7,"minPrice":5099,"banner":null,"lines":null,"dates":null},{"id":"1110009908452311042","goodsCode":"GOODS1110009908452311041","onLine":"Y","isGroup":null,"isDomestic":"N","name":"X+Y  塞班   全新体验","defaultSellNumber":100,"actualSellNumber":0,"whereFrom":null,"whereTo":null,"visa":null,"tags":null,"aroundCity":null,"content":"X+Y  塞班   全新体验","sortIndex":1,"headImg":"http://cyitsapp.oss-cn-beijing.aliyuncs.com/7b8509812b6643f388b45f020e56512f.jpg","days":6,"minPrice":4399,"banner":null,"lines":null,"dates":null},{"id":"1110006480774807555","goodsCode":"GOODS1110006480774807554","onLine":"Y","isGroup":null,"isDomestic":"N","name":"尼泊尔不丹8日9晚","defaultSellNumber":100,"actualSellNumber":0,"whereFrom":null,"whereTo":null,"visa":null,"tags":null,"aroundCity":null,"content":"尼泊尔不丹8日9晚","sortIndex":1,"headImg":"http://cyitsapp.oss-cn-beijing.aliyuncs.com/fe9a3755dadf4f2b9665d2bd95ea75e4.jpg","days":9,"minPrice":18990,"banner":null,"lines":null,"dates":null},{"id":"1110008191060983811","goodsCode":"GOODS1110008191060983810","onLine":"Y","isGroup":null,"isDomestic":"N","name":"触摸印度-劳动最光荣","defaultSellNumber":100,"actualSellNumber":0,"whereFrom":null,"whereTo":null,"visa":null,"tags":null,"aroundCity":null,"content":"触摸印度-劳动最光荣","sortIndex":1,"headImg":"http://cyitsapp.oss-cn-beijing.aliyuncs.com/c4238e503d5e4af58838607655302908.jpg","days":7,"minPrice":5099,"banner":null,"lines":null,"dates":null}]
     * total : 4
     * size : 5
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
         * id : 1110007634489425921
         * goodsCode : GOODS1110007634485231618
         * onLine : Y
         * isGroup : null
         * isDomestic : N
         * name : 触摸印度
         * defaultSellNumber : 100
         * actualSellNumber : 0
         * whereFrom : null
         * whereTo : null
         * visa : null
         * tags : null
         * aroundCity : null
         * content : 触摸印度
         * sortIndex : 1
         * headImg : http://cyitsapp.oss-cn-beijing.aliyuncs.com/443c9097f95f4ecfb0a5e3f8c0e6b3a2.jpg
         * days : 7
         * minPrice : 5099.0
         * banner : null
         * lines : null
         * dates : null
         */

        private String id;
        private String goodsCode;
        private String onLine;
        private Object isGroup;
        private String isDomestic;
        private String name;
        private int defaultSellNumber;
        private int actualSellNumber;
        private Object whereFrom;
        private Object whereTo;
        private Object visa;
        private Object tags;
        private Object aroundCity;
        private String content;
        private int sortIndex;
        private String headImg;
        private int days;
        private double minPrice;
        private Object banner;
        private Object lines;
        private Object dates;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodsCode() {
            return goodsCode;
        }

        public void setGoodsCode(String goodsCode) {
            this.goodsCode = goodsCode;
        }

        public String getOnLine() {
            return onLine;
        }

        public void setOnLine(String onLine) {
            this.onLine = onLine;
        }

        public Object getIsGroup() {
            return isGroup;
        }

        public void setIsGroup(Object isGroup) {
            this.isGroup = isGroup;
        }

        public String getIsDomestic() {
            return isDomestic;
        }

        public void setIsDomestic(String isDomestic) {
            this.isDomestic = isDomestic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDefaultSellNumber() {
            return defaultSellNumber;
        }

        public void setDefaultSellNumber(int defaultSellNumber) {
            this.defaultSellNumber = defaultSellNumber;
        }

        public int getActualSellNumber() {
            return actualSellNumber;
        }

        public void setActualSellNumber(int actualSellNumber) {
            this.actualSellNumber = actualSellNumber;
        }

        public Object getWhereFrom() {
            return whereFrom;
        }

        public void setWhereFrom(Object whereFrom) {
            this.whereFrom = whereFrom;
        }

        public Object getWhereTo() {
            return whereTo;
        }

        public void setWhereTo(Object whereTo) {
            this.whereTo = whereTo;
        }

        public Object getVisa() {
            return visa;
        }

        public void setVisa(Object visa) {
            this.visa = visa;
        }

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public Object getAroundCity() {
            return aroundCity;
        }

        public void setAroundCity(Object aroundCity) {
            this.aroundCity = aroundCity;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getSortIndex() {
            return sortIndex;
        }

        public void setSortIndex(int sortIndex) {
            this.sortIndex = sortIndex;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public double getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(double minPrice) {
            this.minPrice = minPrice;
        }

        public Object getBanner() {
            return banner;
        }

        public void setBanner(Object banner) {
            this.banner = banner;
        }

        public Object getLines() {
            return lines;
        }

        public void setLines(Object lines) {
            this.lines = lines;
        }

        public Object getDates() {
            return dates;
        }

        public void setDates(Object dates) {
            this.dates = dates;
        }
    }
}
