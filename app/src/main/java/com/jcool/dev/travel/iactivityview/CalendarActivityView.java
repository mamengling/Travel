package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelInfoBean;

import java.util.List;

public interface CalendarActivityView extends IBaseActView {
    void excuteSuccessGoodsCallBack(CallBackVo<List<TravelInfoBean.GoodsAndDateBean>> mCallBackVo);

    void excuteSuccessCallBack(CallBackVo<List<TravelInfoBean.GoodsAndDateBean>> mCallBackVo);
}
