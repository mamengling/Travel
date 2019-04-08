package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.VisaBean;

public interface TravelViseActivityView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<VisaBean> mCallBackVo);
}
