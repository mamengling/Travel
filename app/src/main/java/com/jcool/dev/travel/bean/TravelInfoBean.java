package com.jcool.dev.travel.bean;

import java.util.List;

public class TravelInfoBean {

    /**
     * id : 1090890986798440450
     * goodsCode : GOODS1090890986785857537
     * onLine : Y
     * isGroup : Y
     * isDomestic : null
     * name : 日本东京+富士山+京都+大阪7日6晚半自助游(4钻)·品怀石泡温泉·9人团【新春大促】市区4星+箱根温泉·部分私汤【岚山小火车+新干线】蟹道乐+山梨特膳【B线赏樱专线·C线海陆空观富士山】千本鸟居·奈良逗鹿·2日自由
     * defaultSellNumber : 99
     * actualSellNumber : 0
     * whereFrom : 1002
     * whereTo : 1035
     * visa : 10212424112112
     * tags : 樱花,和服,寿司,三文鱼
     * aroundCity : 102,1005,102478
     * content : 日本东京+富士山+京都+大阪7日6晚半自助游(4钻)·品怀石泡温泉·9人团【新春大促】市区4星+箱根温泉·部分私汤【岚山小火车+新干线】蟹道乐+山梨特膳【B线赏樱专线·C线海陆空观富士山】千本鸟居·奈良逗鹿·2日自由
     * sortIndex : 1
     * headImg : http://jcools.oss-cn-qingdao.aliyuncs.com/d123950c911d4ae2be74bf5c69ca4610.png
     * days : 7
     * minPrice : 3999
     * banner : [{"id":"1090890986903298049","joinId":"1090890986798440450","fileUrl":"http://www.img.com/abc.png","sortIndex":1,"businessFlag":"BANNER"},{"id":"1090890986873937921","joinId":"1090890986798440450","fileUrl":"http://www.img.com/abc.png","sortIndex":1,"businessFlag":"BANNER"},{"id":"1090890986915880961","joinId":"1090890986798440450","fileUrl":"http://www.img.com/abc.png","sortIndex":1,"businessFlag":"BANNER"},{"id":"1090890986890715138","joinId":"1090890986798440450","fileUrl":"http://www.img.com/abc.png","sortIndex":1,"businessFlag":"BANNER"}]
     * lines : [{"id":"1090890987062681602","goodsId":"1090890986798440450","lineName":"A路线","sortNumber":0,"characteristic":null,"synopsis":null,"notice":null},{"id":"1090890986932658178","goodsId":"1090890986798440450","lineName":"A路线","sortNumber":0,"characteristic":null,"synopsis":null,"notice":null}]
     * dates : null
     */

    private String id;
    private String goodsCode;
    private String onLine;
    private String isGroup;
    private Object isDomestic;
    private String name;
    private int defaultSellNumber;
    private int actualSellNumber;
    private String whereFrom;
    private String whereTo;
    private String visa;
    private String tags;
    private String aroundCity;
    private String content;
    private int sortIndex;
    private String headImg;
    private int days;
    private int minPrice;
    private Object dates;
    private List<BannerBean> banner;
    private List<LinesBean> lines;

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

    public String getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(String isGroup) {
        this.isGroup = isGroup;
    }

    public Object getIsDomestic() {
        return isDomestic;
    }

    public void setIsDomestic(Object isDomestic) {
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

    public String getWhereFrom() {
        return whereFrom;
    }

    public void setWhereFrom(String whereFrom) {
        this.whereFrom = whereFrom;
    }

    public String getWhereTo() {
        return whereTo;
    }

    public void setWhereTo(String whereTo) {
        this.whereTo = whereTo;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAroundCity() {
        return aroundCity;
    }

    public void setAroundCity(String aroundCity) {
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

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public Object getDates() {
        return dates;
    }

    public void setDates(Object dates) {
        this.dates = dates;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<LinesBean> getLines() {
        return lines;
    }

    public void setLines(List<LinesBean> lines) {
        this.lines = lines;
    }

    public static class BannerBean {
        /**
         * id : 1090890986903298049
         * joinId : 1090890986798440450
         * fileUrl : http://www.img.com/abc.png
         * sortIndex : 1
         * businessFlag : BANNER
         */

        private String id;
        private String joinId;
        private String fileUrl;
        private int sortIndex;
        private String businessFlag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJoinId() {
            return joinId;
        }

        public void setJoinId(String joinId) {
            this.joinId = joinId;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public int getSortIndex() {
            return sortIndex;
        }

        public void setSortIndex(int sortIndex) {
            this.sortIndex = sortIndex;
        }

        public String getBusinessFlag() {
            return businessFlag;
        }

        public void setBusinessFlag(String businessFlag) {
            this.businessFlag = businessFlag;
        }
    }

    public static class LinesBean {
        /**
         * id : 1090890987062681602
         * goodsId : 1090890986798440450
         * lineName : A路线
         * sortNumber : 0
         * characteristic : null
         * synopsis : null
         * notice : null
         */

        private String id;
        private String goodsId;
        private String lineName;
        private int sortNumber;
        private List<CharacteristicBean> characteristic;
        private List<CharacteristicBean> synopsis;
        private List<CharacteristicBean> notice;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getLineName() {
            return lineName;
        }

        public void setLineName(String lineName) {
            this.lineName = lineName;
        }

        public int getSortNumber() {
            return sortNumber;
        }

        public void setSortNumber(int sortNumber) {
            this.sortNumber = sortNumber;
        }

        public List<CharacteristicBean> getCharacteristic() {
            return characteristic;
        }

        public void setCharacteristic(List<CharacteristicBean> characteristic) {
            this.characteristic = characteristic;
        }

        public List<CharacteristicBean> getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(List<CharacteristicBean> synopsis) {
            this.synopsis = synopsis;
        }

        public List<CharacteristicBean> getNotice() {
            return notice;
        }

        public void setNotice(List<CharacteristicBean> notice) {
            this.notice = notice;
        }

        public static class CharacteristicBean {
            /**
             * id : 1090890987062681602
             * goodsId : 1090890986798440450
             * lineName : A路线
             * sortNumber : 0
             * characteristic : null
             * synopsis : null
             * notice : null
             */

            private int viewType;
            private String id;
            private String title;
            private String joinId;
            private String fileUrl;
            private String businessFlag;
            private int sortIndex;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getJoinId() {
                return joinId;
            }

            public void setJoinId(String joinId) {
                this.joinId = joinId;
            }

            public String getFileUrl() {
                return fileUrl;
            }

            public void setFileUrl(String fileUrl) {
                this.fileUrl = fileUrl;
            }

            public String getBusinessFlag() {
                return businessFlag;
            }

            public void setBusinessFlag(String businessFlag) {
                this.businessFlag = businessFlag;
            }

            public int getSortIndex() {
                return sortIndex;
            }

            public void setSortIndex(int sortIndex) {
                this.sortIndex = sortIndex;
            }
        }
    }
}
