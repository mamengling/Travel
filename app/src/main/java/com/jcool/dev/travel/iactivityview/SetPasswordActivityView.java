package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;

public interface SetPasswordActivityView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<String> mCallBackVo);

    void excuteFailedRegieterCallBack(CallBackVo<String> mCallBackVo);
}
