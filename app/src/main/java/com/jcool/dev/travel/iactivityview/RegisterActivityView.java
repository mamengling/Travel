package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.CodeBean;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

public interface RegisterActivityView extends IBaseActView {
    public JSONObject getParamentersRegister();
    public RequestParams getParamentersPhone();

    void excuteSuccessCallBack(CallBackVo mCallBackVo);

    void excuteSuccessCodeCallBack(CallBackVo<CodeBean> mCallBackVo);

    void excuteSuccessUpdateCallBack(CallBackVo mCallBackVo);
}
