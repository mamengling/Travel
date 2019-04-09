package com.jcool.dev.travel.bean;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2019/4/8 23:15
 */

public class VisaInfoBeanView {
    private int viewType;
    private CallBackVo<VisaInfoBean> mCallBackVo;
    private VisaInfoBean visaInfoBean;
    private String title;
    private String imageUrl;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public CallBackVo<VisaInfoBean> getmCallBackVo() {
        return mCallBackVo;
    }

    public void setmCallBackVo(CallBackVo<VisaInfoBean> mCallBackVo) {
        this.mCallBackVo = mCallBackVo;
    }

    public VisaInfoBean getVisaInfoBean() {
        return visaInfoBean;
    }

    public void setVisaInfoBean(VisaInfoBean visaInfoBean) {
        this.visaInfoBean = visaInfoBean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
