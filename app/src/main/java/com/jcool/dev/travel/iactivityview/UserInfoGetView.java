package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.UserInfo;

public interface UserInfoGetView extends IBaseActView {
    void excuteSuccessUserCallBack(CallBackVo<UserInfo.UserInfoBean.SysUserBean> mCallBackVo);

    void excuteSuccessUpdateCallBack(CallBackVo mCallBackVo);
}
