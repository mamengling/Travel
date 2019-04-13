package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.OrderInfoOthBean;

import java.util.List;

public interface AddPersonActivityView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<String> mCallBackVo);
}
