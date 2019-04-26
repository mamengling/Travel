package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.CodeBean;
import com.jcool.dev.travel.bean.UserInfo;

public interface LoginFragmentView extends IBaseActView {

    void excuteSuccessCodeCallBack(CallBackVo<CodeBean> mCallBackVo);

    void excuteSuccessCallBack(CallBackVo<UserInfo> mCallBackVo);
}
