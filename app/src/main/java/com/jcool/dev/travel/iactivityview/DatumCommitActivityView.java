package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;

public interface DatumCommitActivityView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<String> mCallBackVo);
}