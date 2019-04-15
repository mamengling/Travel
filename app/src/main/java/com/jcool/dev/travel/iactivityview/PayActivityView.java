package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.OrderInfoPay;
import com.jcool.dev.travel.bean.OrderInfoPayWx;

public interface PayActivityView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<OrderInfoPay> mCallBackVo);

    void excuteSuccessWxCallBack(CallBackVo<OrderInfoPayWx> mCallBackVo);
}
