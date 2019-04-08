package com.jcool.dev.travel.bean;

import java.util.List;

public class HomeViewBean {
    public static int HOME_VIEW_BANNER = 1;
    public static int HOME_VIEW_ICON = 2;
    public static int HOME_VIEW_GOODS_SALES = 3;
    public static int HOME_VIEW_TAB = 4;
    public static int HOME_VIEW_TAB_LIST = 5;
    private int viewType;
    private List<BannerBean> bannerBeanList;
    private List<HomeIconBean> iconBeanList;
    private List<GoodsBean> iconGoodsList;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public List<BannerBean> getBannerBeanList() {
        return bannerBeanList;
    }

    public void setBannerBeanList(List<BannerBean> bannerBeanList) {
        this.bannerBeanList = bannerBeanList;
    }

    public List<HomeIconBean> getIconBeanList() {
        return iconBeanList;
    }

    public void setIconBeanList(List<HomeIconBean> iconBeanList) {
        this.iconBeanList = iconBeanList;
    }

    public List<GoodsBean> getIconGoodsList() {
        return iconGoodsList;
    }

    public void setIconGoodsList(List<GoodsBean> iconGoodsList) {
        this.iconGoodsList = iconGoodsList;
    }
}
