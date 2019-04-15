package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.OrderVisaInfo;

public interface OtherVisaTabFragmentView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<OrderVisaInfo> mCallBackVo);

    void excuteSuccessOrderCallBack(CallBackVo<String> mCallBackVo);
}
