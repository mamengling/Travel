package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.VisaInfoBean;

public interface VisaInfoActivityView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<VisaInfoBean> mCallBackVo);

    void excuteSuccessCollectCallBack(CallBackVo<String> mCallBackVo);

    void excuteSuccessAddCollectCallBack(CallBackVo mCallBackVo);

    void excuteSuccessDelCollectCallBack(CallBackVo mCallBackVo);
}
