package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.OrderTravelInfoBean;

import java.util.List;

public interface OtherTravelTabFragmentView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<OrderTravelInfoBean> mCallBackVo);

    void excuteSuccessOrderCallBack(CallBackVo<String> mCallBackVo);
}
