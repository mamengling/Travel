package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.TravelInfoBean;
import com.jcool.dev.travel.bean.CallBackVo;

public interface TravelInfoActivityView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<TravelInfoBean> mCallBackVo, CallBackVo<TravelInfoBean.LinesBean> mCallBackVoLine, CallBackVo mCallCollect);
}
