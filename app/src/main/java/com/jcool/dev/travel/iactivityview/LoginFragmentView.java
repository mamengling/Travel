package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.UserInfo;

public interface LoginFragmentView extends IBaseActView {
    void excuteSuccessGoodsCallBack(CallBackVo<UserInfo> mCallBackVo);
}
