package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.TravelInfoBean;
import com.jcool.dev.travel.bean.CallBackVo;

import java.util.List;

public interface TravelInfoActivityView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<TravelInfoBean> mCallBackVo);

    void excuteSuccessLineCallBack(CallBackVo<TravelInfoBean.LinesBean> mCallBackVo);

    void excuteSuccessCollectCallBack(CallBackVo<String> mCallBackVo);

    void excuteSuccessGoodsCallBack(CallBackVo<List<TravelInfoBean.GoodsAndDateBean>> mCallBackVo);

    void excuteSuccessDelCollectCallBack(CallBackVo mCallBackVo);

    void excuteSuccessAddCollectCallBack(CallBackVo mCallBackVo);
}
