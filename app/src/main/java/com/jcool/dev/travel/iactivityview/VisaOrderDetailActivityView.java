package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.VisaOrderInfo;

public interface VisaOrderDetailActivityView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<VisaOrderInfo> mCallBackVo);
}
