package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.CodeBean;

public interface FindPswActivityView extends IBaseActView {
    void excuteSuccessCodeCallBack(CallBackVo<CodeBean> mCallBackVo);

    void excuteSuccessCallBack(CallBackVo<String> mCallBackVo);
}
