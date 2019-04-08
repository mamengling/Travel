package com.jcool.dev.travel.bean;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2019/4/8 21:21
 */

public class TravelInfoBeanView {
    private int viewType;
    private CallBackVo<TravelInfoBean> mCallBackVo;
    private TravelInfoBean.LinesBean.CharacteristicBean itemImage;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public CallBackVo<TravelInfoBean> getmCallBackVo() {
        return mCallBackVo;
    }

    public void setmCallBackVo(CallBackVo<TravelInfoBean> mCallBackVo) {
        this.mCallBackVo = mCallBackVo;
    }

    public TravelInfoBean.LinesBean.CharacteristicBean getItemImage() {
        return itemImage;
    }

    public void setItemImage(TravelInfoBean.LinesBean.CharacteristicBean itemImage) {
        this.itemImage = itemImage;
    }
}
