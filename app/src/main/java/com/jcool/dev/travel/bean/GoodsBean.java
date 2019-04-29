package com.jcool.dev.travel.bean;

import java.util.List;

public class GoodsBean{

    /**
     * records : [{"stockSize":1,"goodsId":"1088701808602472449","salesPrice":999,"isOnline":"Y","id":"1095266166606393345","salesBeginTime":1549964836000,"goodsDate":"1088701808602472449","goodsName":"日本东京+富士山+京都+大阪7日6晚半自助游(4钻)·品怀石泡温泉·9人团【新春大促】市区4星+箱根温泉·部分私汤【岚山小火车+新干线】蟹道乐+山梨特膳【B线赏樱专线·C线海陆空观富士山】千本鸟居·奈良逗鹿·2日自由","salesEndTime":1549964834000},{"stockSize":10,"goodsId":"1088701808602472449","salesPrice":999,"isOnline":"Y","id":"1095561405552353282","salesBeginTime":1549964836000,"goodsDate":"1088701808602472449","goodsName":"日本东京+富士山+京都+大阪7日6晚半自助游(4钻)·品怀石泡温泉·9人团【新春大促】市区4星+箱根温泉·部分私汤【岚山小火车+新干线】蟹道乐+山梨特膳【B线赏樱专线·C线海陆空观富士山】千本鸟居·奈良逗鹿·2日自由","salesEndTime":1550656034000},{"stockSize":10,"goodsId":"1088701808602472449","salesPrice":999,"isOnline":"Y","id":"1095980440756137985","salesBeginTime":1549964836000,"goodsDate":"1088701808602472449","goodsName":"日本东京+富士山+京都+大阪7日6晚半自助游(4钻)·品怀石泡温泉·9人团【新春大促】市区4星+箱根温泉·部分私汤【岚山小火车+新干线】蟹道乐+山梨特膳【B线赏樱专线·C线海陆空观富士山】千本鸟居·奈良逗鹿·2日自由","salesEndTime":1550656034000}]
     * total : 6
     * size : 3
     * current : 1
     * searchCount : true
     * pages : 2
     */

    private String total;
    private String size;
    private String current;
    private boolean searchCount;
    private String pages;
    private List<RecordsBean> records;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
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
         * stockSize : 1
         * goodsId : 1088701808602472449
         * salesPrice : 999
         * isOnline : Y
         * id : 1095266166606393345
         * salesBeginTime : 1549964836000
         * goodsDate : 1088701808602472449
         * goodsName : 日本东京+富士山+京都+大阪7日6晚半自助游(4钻)·品怀石泡温泉·9人团【新春大促】市区4星+箱根温泉·部分私汤【岚山小火车+新干线】蟹道乐+山梨特膳【B线赏樱专线·C线海陆空观富士山】千本鸟居·奈良逗鹿·2日自由
         * salesEndTime : 1549964834000
         */

        private int stockSize;
        private String goodsId;
        private int salesPrice;
        private String isOnline;
        private String id;
        private long salesBeginTime;
        private String goodsDate;
        private String goodsName;
        private String headImg;
        private String state;
        private long salesEndTime;

        public int getStockSize() {
            return stockSize;
        }

        public void setStockSize(int stockSize) {
            this.stockSize = stockSize;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public int getSalesPrice() {
            return salesPrice;
        }

        public void setSalesPrice(int salesPrice) {
            this.salesPrice = salesPrice;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(String isOnline) {
            this.isOnline = isOnline;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getSalesBeginTime() {
            return salesBeginTime;
        }

        public void setSalesBeginTime(long salesBeginTime) {
            this.salesBeginTime = salesBeginTime;
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

        public long getSalesEndTime() {
            return salesEndTime;
        }

        public void setSalesEndTime(long salesEndTime) {
            this.salesEndTime = salesEndTime;
        }
    }
}
